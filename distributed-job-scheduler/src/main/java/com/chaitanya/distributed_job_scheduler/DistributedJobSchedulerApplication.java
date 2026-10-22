package com.chaitanya.distributed_job_scheduler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DistributedJobSchedulerApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(DistributedJobSchedulerApplication.class, args);
	}
}
