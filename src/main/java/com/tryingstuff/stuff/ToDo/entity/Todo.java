package com.tryingstuff.stuff.ToDo.entity;
import java.time.LocalDate;

import com.tryingstuff.stuff.ToDo.enums.TodoStatus;
import jakarta.persistence.*;


@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;


    private String description;

    @ManyToOne
    @JoinColumn(name = "todo_type_id")
    private TodoType todoType;

    @Enumerated(EnumType.STRING)
    private TodoStatus status;


    private LocalDate createdDate;
    private boolean deleted = false;

    public TodoType getTodoType() {
        return todoType;
    }

    public void setTodoType(TodoType todoType) {
        this.todoType = todoType;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public TodoStatus getStatus() {
        return status;
    }

    public void setStatus(TodoStatus status) {
        this.status = status;
    }
}
