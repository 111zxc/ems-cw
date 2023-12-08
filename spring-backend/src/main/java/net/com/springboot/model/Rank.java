package net.javaguides.springboot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ranks")
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    public Rank() {}

    public Rank(String title){
        this.title = title;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() { return this.title; }
    public void setTitle(String title) { this.title = title; }
}
