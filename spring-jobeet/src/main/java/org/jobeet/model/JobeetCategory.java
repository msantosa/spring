package org.jobeet.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Category")
public class JobeetCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;

	@Column(name="name", nullable=false)
	private String name;

	@Column(name="created_at", nullable=false)
	private Date created_at;

	@Column(name="updated_at", nullable=true)
	private Date updated_at;
	
	@OneToMany(targetEntity=JobeetJob.class, fetch = FetchType.LAZY, mappedBy = "category")
	private Set<JobeetJob> trabajos;
	
	public Set<JobeetJob> getTrabajos() {
		return trabajos;
	}

	public void setTrabajos(Set<JobeetJob> trabajos) {
		this.trabajos = trabajos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
}