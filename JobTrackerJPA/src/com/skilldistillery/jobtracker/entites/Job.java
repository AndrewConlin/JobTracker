package com.skilldistillery.jobtracker.entites;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Job {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String title;
	
	private Double salary;
	
	@Column(name="post_url")
	private String postUrl;

	private String description;
	
	@Column(name="application_date")
	private Date applicationDate;
	
	@Column(name="offer_date")
	private Date offerDate;
	
	@ManyToOne
	@JoinColumn(name="status_id")
	private JobStatus status;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="board_id")
	private Board board;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="company_id")
	private Company company;
	
	@JsonIgnore
	@OneToMany(mappedBy="job", cascade=CascadeType.REMOVE)
	private List<Todo> todos;

	@JsonIgnore
	@OneToMany(mappedBy="job", cascade=CascadeType.REMOVE)
	private List<FileLocation> files;

	@JsonIgnore
	@OneToMany(mappedBy="job", cascade=CascadeType.REMOVE)
	private List<Note> notes;

	@OneToMany(mappedBy="job", cascade=CascadeType.REMOVE)
	private List<Contact> contacts;
	
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
	
	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getPostUrl() {
		return postUrl;
	}

	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public Date getOfferDate() {
		return offerDate;
	}

	public void setOfferDate(Date offerDate) {
		this.offerDate = offerDate;
	}

	public List<Todo> getTodos() {
		return todos;
	}

	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}

	public List<FileLocation> getFiles() {
		return files;
	}

	public void setFiles(List<FileLocation> files) {
		this.files = files;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public JobStatus getStatus() {
		return status;
	}

	public void setStatus(JobStatus status) {
		this.status = status;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	// helpers
	public void addTodo(Todo todo) {
		if(todos == null) todos = new ArrayList<>();
		
		if(!todos.contains(todo)) {
			todos.add(todo);
			if(todo.getJob() != null) {
				todo.getJob().getTodos().remove(todo);
			}
			todo.setJob(this);
		}
	}
	
	public void removeTodo(Todo todo) {
		todo.setJob(null);
		if(todos != null) {
			todos.remove(todo);
		}
	}
	
	public void addFile(FileLocation file) {
		if(files == null) files = new ArrayList<>();
		
		if(!files.contains(file)) {
			files.add(file);
			if(file.getJob() != null) {
				file.getJob().getFiles().remove(file);
			}
			file.setJob(this);
		}
	}
	
	public void removeFile(FileLocation file) {
		file.setJob(null);
		if(files != null) {
			files.remove(file);
		}
	}
	
	public void addFile(Note note) {
		if(notes == null) notes = new ArrayList<>();
		
		if(!notes.contains(note)) {
			notes.add(note);
			if(note.getJob() != null) {
				note.getJob().getNotes().remove(note);
			}
			note.setJob(this);
		}
	}
	
	public void removeFile(Note note) {
		note.setJob(null);
		if(notes != null) {
			notes.remove(note);
		}
	}
	
	
	public void addContact(Contact contact) {
		if(contacts == null) contacts = new ArrayList<>();
		
		if(!contacts.contains(contact)) {
			contacts.add(contact);
			if(contact.getJob() != null) {
				contact.getJob().getContacts().remove(contact);
			}
			contact.setJob(this);
		}
	}
	
	public void removeContact(Contact contact) {
		contact.setJob(null);
		if(contacts != null) {
			contacts.remove(contact);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Job [id=").append(id).append(", salary=").append(salary).append(", postUrl=").append(postUrl)
				.append(", description=").append(description).append(", applicationDate=").append(applicationDate)
				.append(", offerDate=").append(offerDate).append("]");
		return builder.toString();
	}
	
	
	
		
}
