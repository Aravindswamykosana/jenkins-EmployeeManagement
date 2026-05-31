package com.aravind.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User1 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	public String name;
	public String email;
	public String password;
	public Long contactNum;
	private String resetToken;
	@CreationTimestamp
	@Column(name="createDate",updatable = false)
	public LocalDateTime createDate;
	@UpdateTimestamp
	@Column(name="updateDate",insertable = false)
	public LocalDateTime updateDate;

}
