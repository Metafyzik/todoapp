package com.example.todoApp.todo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Size(min = 2, max = 25, message = "Title must be between 2 and 25 characters")
    private String title;
    private LocalDateTime created;
    private LocalDateTime adjusted;
    @Size(min = 2, max = 500, message = "Task must be between 2 and 500 characters")
    private String task;

    static Integer numberOfObjects = 0;

    public Item(Integer id, String title, LocalDateTime created, LocalDateTime adjusted, String task) {
        this.id = id;
        this.title = title;
        this.created = created;
        this.adjusted = adjusted;
        this.task = task;

        newObjectCreated();
    }

    static void newObjectCreated () {
        numberOfObjects += 1;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setAdjusted(LocalDateTime adjusted) {
        this.adjusted = adjusted;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getAdjusted() {
        return adjusted;
    }

    public String getTask() {
        return task;
    }
}