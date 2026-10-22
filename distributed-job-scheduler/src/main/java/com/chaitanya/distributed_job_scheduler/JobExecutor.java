package com.chaitanya.distributed_job_scheduler;

import com.chaitanya.distributed_job_scheduler.entity.Job;
import org.springframework.stereotype.Component;

@Component
public class JobExecutor
{
    public void executeTask(Job job)
    {
        System.out.println("Executing Task "+job.getTask());
        System.out.println("Job name is "+job.getName());

        if(job.getTask().equals("SEND_EMAIL"))
        {
            throw new RuntimeException("Simulated email sending failure");
        }
    }
}
