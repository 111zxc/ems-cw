package net.javaguides.springboot.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "employee_tasks",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private Set<Employee> employees = new HashSet<>();

    public Task() {}

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public long getId() {return this.id; }
    public void setId(long Id) {this.id = Id; }

    public String getTitle() {return this.title;}
    public void setTitle(String title) {this.title = title;}
    public String getDescription() {return this.description;}
    public void setDescription(String description) {this.description = description;}

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}

