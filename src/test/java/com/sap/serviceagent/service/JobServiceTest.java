package com.sap.serviceagent.service;

import com.sap.serviceagent.model.Job;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class JobServiceTest {

    private JobService jobService;

    @BeforeEach
    void setUp() {
        jobService = new JobService();
    }

    @Test
    void testAddJob() {
        Job job = new Job();
        job.setName("Test Job");
        job.setEndpoint("/test");
        job.setMethod("POST");
        job.setScheduledTime(OffsetDateTime.now().plusDays(1));

        Job createdJob = jobService.addJob(job);

        assertNotNull(createdJob);
        assertEquals("Test Job", createdJob.getName());
        assertEquals("SCHEDULED", createdJob.getStatus());
    }

    @Test
    void testModifyJob() {
        Job job = new Job();
        job.setName("Original Job");
        job.setEndpoint("/original");
        job.setMethod("GET");
        job.setScheduledTime(OffsetDateTime.now());

        Job createdJob = jobService.addJob(job);

        Job updatedJob = new Job();
        updatedJob.setName("Updated Job");
        updatedJob.setEndpoint("/updated");
        updatedJob.setMethod("PUT");
        updatedJob.setScheduledTime(OffsetDateTime.now());

        Job modifiedJob = jobService.modifyJob(createdJob.getId(), updatedJob);

        assertNotNull(modifiedJob);
        assertEquals("Updated Job", modifiedJob.getName());
        assertEquals("/updated", modifiedJob.getEndpoint());
    }

    @Test
    void testDeleteJobById() {
        Job job = new Job();
        job.setName("Job to be deleted");
        job.setEndpoint("/delete");
        job.setMethod("DELETE");

        Job createdJob = jobService.addJob(job);
        Map<String, String> response = jobService.deleteJobById(createdJob.getId());

        assertEquals("success", response.get("status"));
        assertEquals("Job deleted successfully", response.get("message"));
    }

    @Test
    void testDeleteNonExistentJob() {
        Map<String, String> response = jobService.deleteJobById(999L);

        assertEquals("error", response.get("status"));
        assertEquals("Job not found", response.get("message"));
    }

    @Test
    void testGetAllJobs() {
        Job job1 = new Job();
        job1.setName("Job 1");
        job1.setEndpoint("/job1");
        job1.setMethod("GET");
        jobService.addJob(job1);

        Job job2 = new Job();
        job2.setName("Job 2");
        job2.setEndpoint("/job2");
        job2.setMethod("POST");
        jobService.addJob(job2);

        List<Job> jobs = jobService.getAllJobs();

        assertEquals(2, jobs.size());
    }

    @Test
    void testModifyNonExistentJob() {
        Job updatedJob = new Job();
        updatedJob.setName("Non-existent Job");

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            jobService.modifyJob(999L, updatedJob);
        });

        assertEquals("Job not found with ID: 999", exception.getMessage());
    }
}

