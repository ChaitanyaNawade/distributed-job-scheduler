package com.chaitanya.distributed_job_scheduler.repository;

import com.chaitanya.distributed_job_scheduler.entity.Job;
import com.chaitanya.distributed_job_scheduler.entity.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Long>
{
     public List<Job> findByStatusAndScheduledAtLessThanEqual(JobStatus status, LocalDateTime currentTime);

     public List<Job> findByStatusAndNextAttemptLessThanEqual(JobStatus status, LocalDateTime currentTime);
}
