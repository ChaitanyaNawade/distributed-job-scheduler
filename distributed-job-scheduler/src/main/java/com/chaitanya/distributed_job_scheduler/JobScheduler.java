package com.chaitanya.distributed_job_scheduler;

import com.chaitanya.distributed_job_scheduler.entity.JobStatus;
import com.chaitanya.distributed_job_scheduler.repository.JobRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.chaitanya.distributed_job_scheduler.entity.Job;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
public class JobScheduler
{
    private final JobExecutor jobExecutor;
    private  final JobRepository jobRepository;

    public JobScheduler(JobRepository jobRepository,JobExecutor jobExecutor)
    {
        this.jobRepository = jobRepository;
        this.jobExecutor = jobExecutor;
    }

    @Scheduled(fixedRate = 5000)
    public  void checkForDueJobs()
    {
        LocalDateTime currentTime = LocalDateTime.now();
        List<Job> dueJobs = jobRepository.findByStatusAndScheduledAtLessThanEqual(JobStatus.SCHEDULED , currentTime);
        List<Job> nextAttemptJobs = jobRepository.findByStatusAndNextAttemptLessThanEqual(JobStatus.RETRYING,currentTime);
        List<Job> jobsToProcess = new ArrayList<>(dueJobs);
        jobsToProcess.addAll(nextAttemptJobs);
        System.out.println("Jobs To Process : "+jobsToProcess.size());

        for(Job job : jobsToProcess)
        {
            System.out.println(job.getId());
            System.out.println(job.getName());
            System.out.println(job.getScheduledAt());
            try
            {
                job.setStatus(JobStatus.RUNNING);
                jobRepository.save(job);
                jobExecutor.executeTask(job);
                job.setStatus(JobStatus.SUCCESS);
                jobRepository.save(job);
            }catch(RuntimeException e)
            {
                System.out.println(e.getMessage());
                job.setRetryCount(job.getRetryCount() + 1);
                if(job.getRetryCount() > job.getMaxRetries())
                {
                    job.setStatus(JobStatus.FAILED);
                }
                else
                {
                    job.setStatus(JobStatus.RETRYING);
                    job.setNextAttempt(LocalDateTime.now().plusSeconds(10));
                }
                jobRepository.save(job);
            }
            System.out.println(job.getStatus());
        }
    }

}
