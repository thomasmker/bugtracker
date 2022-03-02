package com.thomashayashi.bugtracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value="/bugs")
public class BugsController {

    @GetMapping
    public void listBugs() {
    }
}
