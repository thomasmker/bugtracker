package com.thomashayashi.bugtracker.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BugTest {
    @Test
    public void whenInstantiated_ThenCreateAnObject() {
        Bug bug = new Bug();
        assertNotNull(bug);
    }
}
