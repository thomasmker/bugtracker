package com.thomashayashi.bugtracker.controller;

import com.thomashayashi.bugtracker.controller.dto.BugDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class BugsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenBugAPICalled_ThenShouldReturnStatus200() throws Exception {
        URI uri = new URI("/bugs");

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    public void whenBugAPICalled_ThenShouldReturnBugInformation() throws Exception {
        URI uri = new URI("/bugs");

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .string("[{\"id\":1,\"title\":\"a\",\"description\":\"b\"},{\"id\":1,\"title\":\"a\",\"description\":\"b\"}]"));
    }
}
