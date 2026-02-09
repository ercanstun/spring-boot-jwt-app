package com.bezkoder.springjwt.metric.controller;

import com.bezkoder.springjwt.metric.service.MetricService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/metrics")
// @CrossOrigin(origins = "*") -> Bunu kaldırmanı öneririm, WebSecurityConfig'deki CORS ayarın yeterli.
public class MetricController {

    private final MetricService metricService;

    public MetricController(MetricService metricService) {
        this.metricService = metricService;
    }

    @PostMapping("/{id}/run")
    public ResponseEntity<?> runMetric(
            @PathVariable Long id,
            @RequestBody(required = false) Object params) { // List yerine Object yaptık

        List<Object> paramList = new java.util.ArrayList<>();

        // Gelen veri bir Liste mi yoksa başka bir şey mi?
        if (params instanceof List) {
            paramList = (List<Object>) params;
        } else if (params instanceof Map) {
            // Eğer Angular boş bir obje {} gönderirse buraya düşer,
            // biz de listeyi boş bırakırız, hata almayız.
        }

        try {
            List<Map<String, Object>> data = metricService.runMetric(id, paramList);

            Map<String, Object> response = new HashMap<>();
            response.put("metricId", id);
            response.put("data", data);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}