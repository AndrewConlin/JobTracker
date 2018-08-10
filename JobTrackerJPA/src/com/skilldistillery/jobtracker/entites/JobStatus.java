package com.skilldistillery.jobtracker.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="job_status")
@Entity
public class JobStatus {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String status;
	
	// gets and sets
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// helpers
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JobStatus [id=").append(id).append(", status=").append(status).append("]");
		return builder.toString();
	}
	
	
}
