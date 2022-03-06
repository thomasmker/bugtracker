package com.thomashayashi.bugtracker.controller;

import com.thomashayashi.bugtracker.mock.BugFormMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class BugsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private URI uri;

    @Before
    public void setUp() throws URISyntaxException {
        uri = new URI("/bugs");
    }

    @Test
    public void whenBugAPIGETIsCalled_ThenShouldReturnStatus200() throws Exception {
        mockMvc.perform(get(uri)).andExpect(status().isOk());
    }

    @Test
    public void whenBugAPIGETIsCalled_ThenReturnShouldBeEncodedInISO88591() throws Exception {
        mockMvc.perform(get(uri)).andExpect(content().encoding("ISO-8859-1"));
    }

    @Test
    public void whenBugAPIGETIsCalled_ThenShouldReturnListOfBugs() throws Exception {
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

    @Test
    public void whenCallingBugAPIPOSTWithNoData_ThenShouldReturnBadRequest() throws Exception {
        mockMvc.perform(post(uri)).andExpect(status().isBadRequest());
    }

    @Test
    public void whenCallingBugAPIPOSTWithValidData_ThenShouldReturnCreated() throws Exception {
        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(BugFormMock.getBugForm())
        ).andExpect(status().isCreated());
    }

    @Test
    public void whenCallingBugAPIPOSTWithValidData_ThenShouldReturnCreatedBugInfo() throws Exception {
        mockMvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(BugFormMock.getBugForm()))
                .andExpect(content().string(containsString("\"title\":\"a\"}")));
    }

    @Test
    public void whenCallingBugAPIPOSTWithValidData_ThenShouldReturnCreatedBugURL() throws Exception {
        mockMvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(BugFormMock.getBugForm()))
                .andExpect(redirectedUrlPattern("**/bugs/{[0-9]+}"));
    }
}
