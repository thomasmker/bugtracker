package com.thomashayashi.bugtracker.controller.form;

import com.thomashayashi.bugtracker.model.Bug;

public class BugForm {
    private String title;

    private String description;

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

    public Bug convert() {
        return new Bug(this.title, this.description);
    }
}
