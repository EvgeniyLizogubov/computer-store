package com.github.evgenylizogubov.computerstore.web;

import com.github.evgenylizogubov.computerstore.model.PersonalComputer;
import com.github.evgenylizogubov.computerstore.repository.PersonalComputerRepository;
import com.github.evgenylizogubov.computerstore.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.evgenylizogubov.computerstore.web.PersonalComputerTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PersonalComputerControllerTest extends AbstractControllerTest {
    private final String REST_URL = "/api/personal-computer";
    private final String REST_URL_SLASH = "/api/personal-computer/";
    
    @Autowired
    private PersonalComputerRepository repository;
    
    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + 1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(PERSONAL_COMPUTER_MATCHER.contentJson(hp));
    }
    
    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(PERSONAL_COMPUTER_MATCHER.contentJson(hp, asus, apple));
    }
    
    @Test
    void createWithLocation() throws Exception {
        PersonalComputer newPersonalComputer = getNew();
        ResultActions actions = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newPersonalComputer)))
                .andExpect(status().isCreated());
        
        PersonalComputer created = PERSONAL_COMPUTER_MATCHER.readFromJson(actions);
        int newId = created.getId();
        newPersonalComputer.setId(newId);
        PERSONAL_COMPUTER_MATCHER.assertMatch(created, newPersonalComputer);
        PERSONAL_COMPUTER_MATCHER.assertMatch(repository.getExisted(newId), newPersonalComputer);
    }
    
    @Test
    void update() throws Exception {
        PersonalComputer updated = getUpdated();
        updated.setId(null);
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + APPLE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());
        
        PERSONAL_COMPUTER_MATCHER.assertMatch(repository.getExisted(APPLE_ID), getUpdated());
    }
}
