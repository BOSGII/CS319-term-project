package com.bosgii.internshipmanagement.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "Document")
public class Document {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    Long requestID;

    String folder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRequestID() {
        return requestID;
    }

    public void setRequestID(Long requestId) {
        this.requestID = requestId;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }



}
