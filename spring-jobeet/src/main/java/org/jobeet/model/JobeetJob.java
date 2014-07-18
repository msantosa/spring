package org.jobeet.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="jobeetjob")
public class JobeetJob {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", nullable=false)
	private Integer id;
	
	@ManyToOne(targetEntity=JobeetCategory.class)
    @ForeignKey(name = "JobeetJob_category_fk")
    @JoinColumn(name = "category_id")
	private Integer category_id;
	
	@Column(name="type", nullable=true)
	private String type;
	
	@Column(name="company", nullable=false)
	private String company;
	
	@Column(name="logo", nullable=true)
	private String logo;
	
	@Column(name="url", nullable=true)
	private String url;
	
	@Column(name="position", nullable=false)
	private String position;
	
	@Column(name="location", nullable=false)
	private String location;
	
	@Column(name="description", nullable=false)
	private String description;
	
	@Column(name="how_to_apply", nullable=false)
	private String how_to_apply;
	
	@Column(name="token", unique=true, nullable=false)
	private String token;
	
	@Column(name="is_public", nullable=false)
	private boolean is_public;
	
	@Column(name="is_actived", nullable=false)
	private boolean is_actived;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@Column(name="expires_at", nullable=false)
	private Timestamp expires_at;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isIs_actived() {
		return is_actived;
	}

	public void setIs_actived(boolean is_actived) {
		this.is_actived = is_actived;
	}

	public Timestamp getExpires_at() {
		return expires_at;
	}

	public void setExpires_at(Timestamp expires_at) {
		this.expires_at = expires_at;
	}
}
