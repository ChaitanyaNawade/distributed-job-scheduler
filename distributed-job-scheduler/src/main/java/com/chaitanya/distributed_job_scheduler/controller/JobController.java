package com.chaitanya.distributed_job_scheduler.controller;

import com.chaitanya.distributed_job_scheduler.dto.CreateJobRequest;
import com.chaitanya.distributed_job_scheduler.entity.Job;
import com.chaitanya.distributed_job_scheduler.service.JobService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController
{
    private  final JobService jobService;

    public JobController(JobService jobService)
    {
        this.jobService = jobService;
    }


    @PostMapping()
    public Job saveJob(@Valid @RequestBody CreateJobRequest createJobRequest)
    {
        return jobService.saveJob(createJobRequest);
    }

    @GetMapping()
    public List<Job> getAllJobs()
    {
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public Job getJobById(@PathVariable Long id)
    {
        return jobService.getJobById(id);
    }
}
