package com.thomashayashi.bugtracker.controller.dto;

import com.thomashayashi.bugtracker.model.Bug;

public class BugDto {
    private Long id;
    private String title;

    public BugDto() {

    }

    public BugDto(Bug bug) {
        this.id = bug.getId();
        this.title = bug.getTitle();
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
}
