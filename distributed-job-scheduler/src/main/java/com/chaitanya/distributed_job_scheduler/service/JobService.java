package com.chaitanya.distributed_job_scheduler.service;

import com.chaitanya.distributed_job_scheduler.dto.CreateJobRequest;
import com.chaitanya.distributed_job_scheduler.dto.JobResponse;
import com.chaitanya.distributed_job_scheduler.entity.Job;
import com.chaitanya.distributed_job_scheduler.entity.JobStatus;
import com.chaitanya.distributed_job_scheduler.exception.JobNotFoundException;
import com.chaitanya.distributed_job_scheduler.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobService
{
    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository)
    {
        this.jobRepository = jobRepository;
    }

    public JobResponse saveJob(CreateJobRequest createJobRequest)
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
        Job savedJob = jobRepository.save(newJob);
        JobResponse jobResponse = new JobResponse();

        jobResponse.setId(savedJob.getId());
        jobResponse.setName(savedJob.getName());
        jobResponse.setTask(savedJob.getTask());
        jobResponse.setStatus(savedJob.getStatus());
        jobResponse.setScheduledAt(savedJob.getScheduledAt());
        jobResponse.setPriority(savedJob.getPriority());

        return jobResponse;
    }

    public List<JobResponse> getAllJobs()
    {
        List<JobResponse> responses = new ArrayList<>();

        List<Job> jobs = jobRepository.findAll();

        for(Job job : jobs)
        {
            JobResponse response = new JobResponse();
            response.setId(job.getId());
            response.setName(job.getName());
            response.setTask(job.getTask());
            response.setStatus(job.getStatus());
            response.setScheduledAt(job.getScheduledAt());
            response.setPriority(job.getPriority());
            responses.add(response);
        }

        return responses;
    }

    public JobResponse getJobById(Long id)
    {
        Job job =  jobRepository.findById(id).
                orElseThrow(()->new JobNotFoundException("Job not found with id : "+id));

        JobResponse response = new JobResponse();

        response.setId(job.getId());
        response.setName(job.getName());
        response.setTask(job.getTask());
        response.setStatus(job.getStatus());
        response.setScheduledAt(job.getScheduledAt());
        response.setPriority(job.getPriority());

        return response;
    }
}
