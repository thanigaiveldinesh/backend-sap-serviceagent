package com.sap.serviceagent.controller;

import com.sap.serviceagent.model.Job;
import com.sap.serviceagent.service.JobService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class JobManagementControllerTest {

    @InjectMocks
    private JobManagementController jobManagementController;

    @Mock
    private JobService jobService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateJob() {
        Job job = new Job();
        job.setName("New Job");
        job.setEndpoint("/new");
        job.setMethod("POST");
        job.setScheduledTime(OffsetDateTime.now().plusDays(1));

        when(jobService.addJob(any(Job.class))).thenReturn(job);

        ResponseEntity<Job> response = jobManagementController.createJob(job);
        assertEquals(HttpStatus.CREATED, response.getStatusCode()); // compare enums
        assertEquals("New Job", response.getBody().getName());
    }

    @Test
    void testUpdateJob() {
        Job updatedJob = new Job();
        updatedJob.setName("Updated Job");

        when(jobService.modifyJob(any(Long.class), any(Job.class))).thenReturn(updatedJob);

        ResponseEntity<Job> response = jobManagementController.updateJob(1L, updatedJob);
        assertEquals(HttpStatus.OK, response.getStatusCode()); // compare enums
        assertEquals("Updated Job", response.getBody().getName());
    }

    @Test
    void testRemoveJob() {
        Map<String, String> responseMap = Map.of("status", "success", "message", "Job deleted successfully");
        when(jobService.deleteJobById(any(Long.class))).thenReturn(responseMap);

        ResponseEntity<Map<String, String>> response = jobManagementController.removeJob(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode()); // compare enums
        assertEquals("success", response.getBody().get("status"));
    }

    @Test
    void testFetchAllJobs() {
        Job job1 = new Job();
        job1.setName("Job 1");
        Job job2 = new Job();
        job2.setName("Job 2");

        when(jobService.getAllJobs()).thenReturn(List.of(job1, job2));

        ResponseEntity<List<Job>> response = jobManagementController.fetchAllJobs();
        assertEquals(HttpStatus.OK, response.getStatusCode()); // compare enums
        assertEquals(2, response.getBody().size());
    }
}
