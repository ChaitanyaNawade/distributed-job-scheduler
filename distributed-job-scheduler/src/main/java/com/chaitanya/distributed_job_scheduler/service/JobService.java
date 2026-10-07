package com.chaitanya.distributed_job_scheduler.service;

import com.chaitanya.distributed_job_scheduler.dto.CreateJobRequest;
import com.chaitanya.distributed_job_scheduler.entity.Job;
import com.chaitanya.distributed_job_scheduler.entity.JobStatus;
import com.chaitanya.distributed_job_scheduler.exception.JobNotFoundException;
import com.chaitanya.distributed_job_scheduler.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobService
{
    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository)
    {
        this.jobRepository = jobRepository;
    }

    public Job saveJob(CreateJobRequest createJobRequest)
    {
        Job newJob = new Job();
        LocalDateTime now = LocalDateTime.now();

        newJob.setName(createJobRequest.getName());
        newJob.setTask(createJobRequest.getTask());
        newJob.setScheduledAt(createJobRequest.getScheduledAt());
        newJob.setPriority(createJobRequest.getPriority());
        newJob.setMaxRetries(createJobRequest.getMaxRetries());
        newJob.setUpdatedAt(now);
        newJob.setCreatedAt(now);
        newJob.setStatus(JobStatus.SCHEDULED);
        newJob.setRetryCount(0);
        return jobRepository.save(newJob);
    }

    public List<Job> getAllJobs()
    {
        return jobRepository.findAll();
    }

    public Job getJobById(Long id)
    {
        return jobRepository.findById(id).
                orElseThrow(()->new JobNotFoundException("Job not found with id : "+id));
    }
}
