package com.bpl.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="REST_TOURIEST")
@AllArgsConstructor
@NoArgsConstructor
public class Touriest implements Serializable {

	@Id
	@SequenceGenerator(name="gen1",sequenceName = "SEQ_TOURIEST",initialValue = 1000,allocationSize = 1)
	@GeneratedValue(generator = "gen1",strategy = GenerationType.SEQUENCE)
	private Integer tid;
	@Nonnull
	@Column(length=20)
	private String name;
	@Nonnull
	@Column(length=20)
	private String address;
	@Nonnull
	@Column(length=20)
	private String packageType;
	@Nonnull
	private Double budget;
	@Nonnull
	private LocalDateTime startDate;
	@Nonnull
	private LocalDateTime endDate;
	
	
	
	
	
}
