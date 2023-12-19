package com.brickup.backend.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

    private String title;
    private boolean completed;
    
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] photoPath;

    public Task() {
    }

    public Task(String title, boolean completed, byte[] photoPath) {
        this.title = title;
        this.completed = completed;
        this.photoPath = photoPath;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public byte[] getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(byte[] photoPath) {
        this.photoPath = photoPath;
    }
    
    @Override
    public String toString() {
        return "Task{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", completed=" + completed +
            ", photoPath='" + photoPath + '\'' +
            '}';
    	}
}
