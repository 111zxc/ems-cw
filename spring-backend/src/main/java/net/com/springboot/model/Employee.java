package net.javaguides.springboot.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email_id")
	private String emailId;

	@Column(name = "department_id")
	private long departmentId;

	@Column(name = "rank_id")
	private long rankId;

	@ManyToMany(mappedBy = "employees")
	private Set<Task> tasks = new HashSet<>();
	
	public Employee() {
		
	}
	
	public Employee(String firstName, String lastName, String emailId, long departmentId, long rankId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.departmentId = departmentId;
		this.rankId = rankId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public long getDepartmentId() { return departmentId; }
	public void setDepartmentId(long departmentId) {this.departmentId = departmentId; }

	public long getRankId() { return rankId; }
	public void setRankId(long rankId) {this.rankId = rankId;}

}
