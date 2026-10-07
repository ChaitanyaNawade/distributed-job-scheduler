package com.chaitanya.distributed_job_scheduler.exception;

public class JobNotFoundException extends RuntimeException
{
    public JobNotFoundException(String message)
    {
        super(message);
    }
}
