package com.thomashayashi.bugtracker.controller;

import com.thomashayashi.bugtracker.controller.dto.BugDetailsDto;
import com.thomashayashi.bugtracker.controller.dto.BugDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
    public List<BugDto> bugList() {
        BugDto bug = new BugDto();
        bug.setId(1L);
        bug.setTitle("a");

        return Arrays.asList(bug, bug);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BugDetailsDto> bugDetails(@PathVariable("id") Long id)
    {
        if(id == 1) {
            BugDetailsDto bug = new BugDetailsDto();
            bug.setId(1L);
            bug.setTitle("a");
            bug.setDescription("b");
            return ResponseEntity.ok(bug);
        }

        return ResponseEntity.notFound().build();
    }
}
