package com.pingr.accounts.Account;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_seq_generator",
            sequenceName = "course_seq_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_seq_generator"
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            unique = true
    )
    private String name;

    @ManyToMany(mappedBy = "likedCourses")
    private Set<Account> likes = new HashSet<>();

    public Course(Long id, String name, Set<Account> accounts) {
        this.id = id;
        this.name = name;
        this.likes = accounts;
    }

    public Course(String name, Set<Account> accounts) {
        this.name = name;
        this.likes = accounts;
    }

    public Course() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Account> getLikes() {
        return likes;
    }

    public void setLikes(Set<Account> accounts) {
        this.likes = accounts;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", likes=" + likes +
                '}';
    }
}
