package com.thomashayashi.bugtracker.controller;

import com.thomashayashi.bugtracker.mock.BugFormMock;
import org.junit.Before;
import org.junit.Test;
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
    public void whenGetIsCalled_ThenShouldReturnOkEncodedInISO88591WithListOfBugs() throws Exception {
        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andExpect(content().encoding("ISO-8859-1"))
                .andExpect(content().string("[{\"id\":1,\"title\":\"a\"},{\"id\":1,\"title\":\"a\"}]"));
    }

    @Test
    public void whenGetIsCalledWithInvalidID_ThenShouldReturnNotFound() throws Exception {
        URI uri = new URI("/bugs/-1");
        mockMvc.perform(get(uri)).andExpect(status().isNotFound());
    }

    @Test
    public void whenGetIsCalledWithValidID_ThenShouldReturnTheBugWithThatID() throws Exception {
        URI uri = new URI("/bugs/1");

        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"title\":\"a\",\"description\":\"b\"}"));
    }

    @Test
    public void whenDeleteIsCalledWithInvalidID_ThenShouldReturnNotFound() throws Exception {
        URI uri = new URI("/bugs/-1");
        mockMvc.perform(delete(uri)).andExpect(status().isNotFound());
    }

    @Test
    public void whenDeleteIsCalledWithValidID_ThenShouldReturnOK() throws Exception {
        URI uri = new URI("/bugs/1");
        mockMvc.perform(delete(uri)).andExpect(status().isOk());
    }

    @Test
    public void whenPostIsCalledWithNoData_ThenShouldReturnBadRequest() throws Exception {
        mockMvc.perform(post(uri)).andExpect(status().isBadRequest());
    }

    @Test
    public void whenPostIsCalledWithValidData_ThenShouldReturnCreatedAndTheBugInfo() throws Exception {
        mockMvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(BugFormMock.getBugForm()))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("\"title\":\"a\"}")));
    }

    @Test
    public void whenPostIsCalledWithValidData_ThenShouldReturnCreatedBugURL() throws Exception {
        mockMvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(BugFormMock.getBugForm()))
                .andExpect(redirectedUrlPattern("**/bugs/{[0-9]+}"));
    }

    @Test
    public void whenPutIsCalledWithNoData_ThenShouldReturnBadRequest() throws Exception {
        URI uri = new URI("/bugs/1");
        mockMvc.perform(put(uri)).andExpect(status().isBadRequest());
    }

    @Test
    public void whenPutIsCalledWithInvalidID_ThenShouldReturnNotFound() throws Exception {
        URI uri = new URI("/bugs/-1");
        mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"description\":\"b\"}"))
            .andExpect(status().isNotFound());
    }

    @Test
    public void whenPutIsCalledWithValidIDAndData_ThenShouldReturnOkWithTheBugInfo() throws Exception {
        URI uri = new URI("/bugs/1");
        mockMvc.perform(put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\":\"b\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":1,\"title\":")));
    }
}
