package com.bezkoder.springjwt.metric.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "metrics")
public class Metric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String description;

    @Column(name = "target_db")
    private String targetDb;

    @Column(name = "sql_text", columnDefinition = "TEXT")
    private String sqlText;

    private Boolean active;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // --- getters setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getTargetDb() { return targetDb; }
    public void setTargetDb(String targetDb) { this.targetDb = targetDb; }

    public String getSqlText() { return sqlText; }
    public void setSqlText(String sqlText) { this.sqlText = sqlText; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
