package com.sap.serviceagent.controller;

import com.sap.serviceagent.model.Job;
import com.sap.serviceagent.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class JobManagementController {

    @Autowired
    private JobService jobService;

    @PostMapping
    public ResponseEntity<Job> createJob(@Valid @RequestBody Job newJob) {
        Job createdJob = jobService.addJob(newJob);
        return ResponseEntity.status(201).body(createdJob);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @Valid @RequestBody Job jobUpdates) {
        Job updatedJob = jobService.modifyJob(id, jobUpdates);
        return ResponseEntity.ok(updatedJob);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> removeJob(@PathVariable Long id) {
        Map<String, String> response = jobService.deleteJobById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Job>> fetchAllJobs() {
        List<Job> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }
}
