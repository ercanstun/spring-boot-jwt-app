package com.bezkoder.springjwt.metric.repository;

import com.bezkoder.springjwt.metric.entity.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricRepository extends JpaRepository<Metric, Long> {
}
