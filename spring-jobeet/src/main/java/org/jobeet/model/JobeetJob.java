package org.jobeet.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class JobeetJob {

	private int id;
	
	private int category_id;
	
	private String type;
	
	private String company;
	
	private String logo;
	
	private String position;
	
	private String location;
	
	private String description;
	
	private String how_to_apply;
	
	private String token;
	
	private boolean is_public=true;
	
	private boolean is_actived=false;
	
	private String email;
	
	private Timestamp expires_at;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
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

	public boolean isIs_actived() {
		return is_actived;
	}

	public void setIs_actived(boolean is_actived) {
		this.is_actived = is_actived;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getExpires_at() {
		return expires_at;
	}

	public void setExpires_at(Timestamp expires_at) {
		this.expires_at = expires_at;
	}	
}
