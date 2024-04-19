package org.geekhub.kukotin.coursework.service.document;

import java.time.Instant;

public class Document {

    private int documentId;
    private int documentType;
    private String title;
    private String description;
    private int publishingYear;
    private int authorId;
    private int availableCopies;
    private Instant lastUpdatedTime;
    private int updatedByUserId;
    private int topicId;
    private int publisherId;

    public Document(int documentId, int documentType, String title, String description,
                    int publishingYear, int authorId, int availableCopies, Instant lastUpdatedTime,
                    int updatedByUserId, int topicId, int publisherId) {
        this.documentId = documentId;
        this.documentType = documentType;
        this.title = title;
        this.description = description;
        this.publishingYear = publishingYear;
        this.authorId = authorId;
        this.availableCopies = availableCopies;
        this.lastUpdatedTime = lastUpdatedTime;
        this.updatedByUserId = updatedByUserId;
        this.topicId = topicId;
        this.publisherId = publisherId;
    }

    public int getDocumentId() {
        return documentId;
    }

    public int getDocumentType() {
        return documentType;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public Instant getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public int getUpdatedByUserId() {
        return updatedByUserId;
    }

    public int getTopicId() {
        return topicId;
    }

    public int getPublisherId() {
        return publisherId;
    }
}
