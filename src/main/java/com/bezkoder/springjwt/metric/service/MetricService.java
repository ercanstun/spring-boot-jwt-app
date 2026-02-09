package com.bezkoder.springjwt.metric.service;

import com.bezkoder.springjwt.metric.entity.Metric;
import com.bezkoder.springjwt.metric.repository.MetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate; // Bunu ekledik
import org.springframework.stereotype.Service;
import javax.sql.DataSource; // Import ekle
import java.util.List;
import java.util.Map;

@Service
public class MetricService {

    private final MetricRepository metricRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MetricService(
            MetricRepository metricRepository,
            @Qualifier("erpJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.metricRepository = metricRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> runMetric(Long metricId, List<Object> params) {
        Metric metric = metricRepository.findById(metricId)
                .orElseThrow(() -> new RuntimeException("Metric not found: " + metricId));

        if (params != null && !params.isEmpty()) {
            // List'i diziye çevirip JdbcTemplate'e öyle veriyoruz
            return jdbcTemplate.queryForList(metric.getSqlText(), params.toArray());
        } else {
            return jdbcTemplate.queryForList(metric.getSqlText());
        }
    }
}