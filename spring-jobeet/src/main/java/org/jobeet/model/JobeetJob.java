package org.jobeet.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Job")
public class JobeetJob {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", nullable=false)
	private Integer id;
	
	/*@ManyToOne(targetEntity=JobeetCategory.class)
    @ForeignKey(name = "JobeetJob_category_fk")
    @JoinColumn(name = "category_id")*/
	//@Column(name="category_id")
	//private Integer category_id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = true)
	private JobeetCategory category;
	
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
	
	@Column(name="is_activated", nullable=false)
	private boolean is_activated;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@Column(name="expires_at", nullable=false)
	private Date expires_at;
	
	@Column(name="created_at", nullable=false)
	private Date created_at;
	
	@Column(name="updated_at", nullable=true)
	private Date updated_at;
	
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
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/*public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}*/

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

	public boolean isIs_activated() {
		return is_activated;
	}

	public void setIs_activated(boolean is_activated) {
		this.is_activated = is_activated;
	}

	public Date getExpires_at() {
		return expires_at;
	}

	public void setExpires_at(Date expires_at) {
		this.expires_at = expires_at;
	}

	public JobeetCategory getCategory() {
		return category;
	}

	public void setCategory(JobeetCategory category) {
		this.category = category;
	}
}
