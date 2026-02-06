package com.bezkoder.springjwt.metric.controller;

import com.bezkoder.springjwt.metric.service.MetricService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/metrics")
public class MetricController {

    private final MetricService metricService;

    public MetricController(MetricService metricService) {
        this.metricService = metricService;
    }

    @GetMapping("/{id}/run")
    public ResponseEntity<?> runMetric(@PathVariable Long id) {

        List<Map<String, Object>> data = metricService.runMetric(id);

        Map<String, Object> response = new HashMap<>();
        response.put("metricId", id);
        response.put("rowCount", data.size());
        response.put("data", data);

        return ResponseEntity.ok(response);
    }
}
