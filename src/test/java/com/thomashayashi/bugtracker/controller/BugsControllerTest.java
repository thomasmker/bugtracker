package com.thomashayashi.bugtracker.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class BugsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenBugAPIGETIsCalled_ThenShouldReturnStatus200() throws Exception {
        URI uri = new URI("/bugs");
        mockMvc.perform(get(uri)).andExpect(status().isOk());
    }

    @Test
    public void whenBugAPIGETIsCalled_ThenReturnShouldBeEncodedInISO88591() throws Exception {
        URI uri = new URI("/bugs");
        mockMvc.perform(get(uri)).andExpect(content().encoding("ISO-8859-1"));
    }

    @Test
    public void whenBugAPIGETIsCalled_ThenShouldReturnListOfBugs() throws Exception {
        URI uri = new URI("/bugs");

        mockMvc.perform(get(uri))
                .andExpect(content().string("[{\"id\":1,\"title\":\"a\"},{\"id\":1,\"title\":\"a\"}]"));
    }

    @Test
    public void whenCallingBugAPIGETWithInvalidID_ThenShouldReturnNotFound() throws Exception {
        URI uri = new URI("/bugs/-1");
        mockMvc.perform(get(uri)).andExpect(status().isNotFound());
    }

    @Test
    public void whenCallingBugAPIGETWithValidID_ThenShouldReturnTheBugWithThatID() throws Exception {
        URI uri = new URI("/bugs/1");

        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"title\":\"a\",\"description\":\"b\"}"));
    }

    @Test
    public void whenCallingBugAPIDELETEWithInvalidID_ThenShouldReturnNotFound() throws Exception {
        URI uri = new URI("/bugs/-1");
        mockMvc.perform(delete(uri)).andExpect(status().isNotFound());
    }

    @Test
    public void whenCallingBugAPIDELETEWithValidID_ThenShouldReturnOK() throws Exception {
        URI uri = new URI("/bugs/1");
        mockMvc.perform(delete(uri)).andExpect(status().isOk());
    }


}
