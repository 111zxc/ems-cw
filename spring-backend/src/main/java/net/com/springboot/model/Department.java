package net.javaguides.springboot.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    public Department() {}

    public Department(String name) {
        super();
        this.name = name;
    }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

}
