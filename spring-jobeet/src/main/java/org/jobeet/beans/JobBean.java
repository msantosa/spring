package org.jobeet.beans;

import java.util.Date;

import org.jobeet.model.JobeetCategory;

public class JobBean {
	private Integer id;

	private JobeetCategory category;

	private String type;

	private String company;

	private String logo;

	private String url;

	private String position;

	private String location;

	private String description;

	private String how_to_apply;

	private String token;

	private boolean is_public=false;

	private boolean is_activated=false;

	private String email;

	private Date expires_at;

	private Date created_at;

	private Date updated_at;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public JobeetCategory getCategory() {
		return category;
	}

	public void setCategory(JobeetCategory category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHow_to_apply() {
		return how_to_apply;
	}

	public void setHow_to_apply(String how_to_apply) {
		this.how_to_apply = how_to_apply;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isIs_public() {
		return is_public;
	}

	public void setIs_public(boolean is_public) {
		this.is_public = is_public;
	}

	public boolean isIs_activated() {
		return is_activated;
	}

	public void setIs_activated(boolean is_activated) {
		this.is_activated = is_activated;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getExpires_at() {
		return expires_at;
	}

	public void setExpires_at(Date expires_at) {
		this.expires_at = expires_at;
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
