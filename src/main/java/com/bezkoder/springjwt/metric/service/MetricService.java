package com.bezkoder.springjwt.metric.service;

import com.bezkoder.springjwt.metric.entity.Metric;
import com.bezkoder.springjwt.metric.repository.MetricRepository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MetricService {

    private final MetricRepository metricRepository;
    private final JdbcTemplate classicJdbcTemplate;

    public MetricService(
            MetricRepository metricRepository,
            @Qualifier("classicJdbcTemplate") JdbcTemplate classicJdbcTemplate
    ) {
        this.metricRepository = metricRepository;
        this.classicJdbcTemplate = classicJdbcTemplate;
    }

    public List<Map<String, Object>> runMetric(Long metricId) {

        Metric metric = metricRepository.findById(metricId)
                .orElseThrow(() -> new RuntimeException("Metric not found: " + metricId));

        if (!Boolean.TRUE.equals(metric.getActive())) {
            throw new RuntimeException("Metric inactive");
        }

        if (!"classicmodels".equals(metric.getTargetDb())) {
            throw new RuntimeException("Unsupported target_db: " + metric.getTargetDb());
        }

        return classicJdbcTemplate.queryForList(metric.getSqlText());
    }
}
