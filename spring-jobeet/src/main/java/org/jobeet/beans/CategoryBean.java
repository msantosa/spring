package org.jobeet.beans;

import java.util.Date;
import java.util.Set;

public class CategoryBean {
	private Integer id;

	private String name;

	private Date created_at;

	private Date updated_at;
	
	private Set<Integer> trabajos;

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

	public Set<Integer> getTrabajos() {
		return trabajos;
	}

	public void setTrabajos(Set<Integer> trabajos) {
		this.trabajos = trabajos;
	}
	
	
}
