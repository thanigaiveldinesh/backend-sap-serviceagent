package com.sap.serviceagent.service;

import com.sap.serviceagent.model.Job;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class JobService {
    private final List<Job> jobList = new ArrayList<>();
    private final AtomicLong jobCounter = new AtomicLong(1);

    public Job addJob(Job job) {
        job.setId(jobCounter.getAndIncrement());
        if (job.getScheduledTime() != null && job.getScheduledTime().isAfter(OffsetDateTime.now())) {
            job.setStatus("SCHEDULED");
        } else {
            job.setStatus("EXECUTED");
        }
        jobList.add(job);
        return job;
    }

    public Job modifyJob(Long id, Job updatedJob) {
        for (Job job : jobList) {
            if (job.getId().equals(id)) {
                job.setName(updatedJob.getName());
                job.setEndpoint(updatedJob.getEndpoint());
                job.setMethod(updatedJob.getMethod());
                job.setHeaders(updatedJob.getHeaders());
                job.setBody(updatedJob.getBody());
                job.setScheduledTime(updatedJob.getScheduledTime());
                job.setStatus(updatedJob.getScheduledTime() != null && updatedJob.getScheduledTime().isAfter(OffsetDateTime.now())
                        ? "SCHEDULED" : "EXECUTED");
                return job;
            }
        }
        throw new NoSuchElementException("Job not found with ID: " + id);
    }

    public Map<String, String> deleteJobById(Long id) {
        boolean removed = jobList.removeIf(job -> job.getId().equals(id));
        return removed ? Map.of("status", "success", "message", "Job deleted successfully")
                : Map.of("status", "error", "message", "Job not found");
    }

    public List<Job> getAllJobs() {
        return new ArrayList<>(jobList);
    }
}
