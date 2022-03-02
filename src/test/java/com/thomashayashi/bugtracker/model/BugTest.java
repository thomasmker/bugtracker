package com.thomashayashi.bugtracker.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class BugTest {
    private Bug bug;

    @Before
    public void setUp() {
        bug = new Bug();
    }

    @Test
    public void whenInstantiated_ThenCreateAnObject() {
        assertNotNull(bug);
    }

    @Test
    public void whenCreated_ThenTheCreationDateIsNow() {
        assertEquals(LocalDateTime.now().withNano(0), bug.getCreationDate().withNano(0));
    }

    @Test
    public void whenCreated_ThenTheStatusIsOpen() {
        assertEquals(BugStatus.OPEN, bug.getStatus());
    }
}
