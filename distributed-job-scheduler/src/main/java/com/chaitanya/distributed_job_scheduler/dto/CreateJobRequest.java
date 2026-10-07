package com.chaitanya.distributed_job_scheduler.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class CreateJobRequest
{
    @NotBlank
    private String name;
    @NotBlank
    private String task;
    @NotNull
    private LocalDateTime scheduledAt;
    @Positive
    private int priority;
    @Min(value = 0, message = "value must be at least 0")
    @Max(value = 10 , message = "value must be no more than 10")
    private int maxRetries;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }


}
