package com.thomashayashi.bugtracker.controller;

import com.thomashayashi.bugtracker.controller.dto.BugDetailsDto;
import com.thomashayashi.bugtracker.controller.dto.BugDto;
import com.thomashayashi.bugtracker.controller.form.BugForm;
import com.thomashayashi.bugtracker.controller.form.UpdateBugForm;
import com.thomashayashi.bugtracker.model.Bug;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value="/bugs")
public class BugsController {

    @GetMapping
    public List<BugDto> getBugs() {
        BugDto bug = new BugDto();
        bug.setId(1L);
        bug.setTitle("a");

        return Arrays.asList(bug, bug);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BugDetailsDto> getBugDetails(@PathVariable("id") Long id)
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

    @PostMapping
    public ResponseEntity<BugDto> createBug(@RequestBody BugForm form, UriComponentsBuilder uriBuilder)
    {
        Bug bug = form.convert();
        //Save bug
        bug.setId(11L);
        URI uri = uriBuilder.path("/bugs/{id}").buildAndExpand(bug.getId()).toUri();
        return ResponseEntity.created(uri).body(new BugDto(bug));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BugDto> updateBug(@PathVariable("id") Long id, @RequestBody UpdateBugForm form)
    {
        if(id == 1) {
            BugDto bug = new BugDto();
            bug.setId(1L);
            return ResponseEntity.ok(bug);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBug(@PathVariable("id") Long id)
    {
        if(id == 1)
            return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }
}
