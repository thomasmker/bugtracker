package com.thomashayashi.bugtracker.controller;

import com.thomashayashi.bugtracker.controller.dto.BugDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value="/bugs")
public class BugsController {

    @GetMapping
    public List<BugDto> listBugs() {
        BugDto bug = new BugDto();
        bug.setId(1L);
        bug.setTitle("a");
        bug.setDescription("b");

        return Arrays.asList(bug, bug);
    }
}
