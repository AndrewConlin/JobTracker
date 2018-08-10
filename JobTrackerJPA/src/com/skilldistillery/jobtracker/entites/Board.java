package com.skilldistillery.jobtracker.entites;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Board {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String title;
	
	private String description;
	
	@Column(name="create_date")
	@CreationTimestamp
	private Date createDate;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy="board")
	private List<Job> jobs;
	
	// gets and sets

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
	
	// helpers
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Board [id=").append(id).append(", title=").append(title).append(", description=")
				.append(description).append(", createDate=").append(createDate).append("]");
		return builder.toString();
	}
	
	public void addJob(Job job) {
		if(jobs == null) jobs = new ArrayList<>();
		
		if(!jobs.contains(job)) {
			jobs.add(job);
			if(job.getBoard() != null) {
				job.getBoard().getJobs().remove(job);
			}
			job.setBoard(this);
		}
	}
	
	public void removeFile(Job job) {
		job.setBoard(null);
		if(jobs != null) {
			jobs.remove(job);
		}
	}
}