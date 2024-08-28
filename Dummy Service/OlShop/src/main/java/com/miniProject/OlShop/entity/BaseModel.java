package com.miniProject.OlShop.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public class BaseModel {
	@Id
	private String id;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@Column(name = "version", nullable = false)
	@Version
	private Integer ver;

    @PrePersist
	private void preInsert() {
		this.id = UUID.randomUUID().toString();
		this.createdAt = LocalDateTime.now();
	}

	@PreUpdate
	private void preUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
}
