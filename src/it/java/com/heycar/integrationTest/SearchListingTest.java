package com.heycar.integrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heycar.codingchallenge.HeyCarApplication;
import com.heycar.codingchallenge.controller.CarListingSearchController;
import com.heycar.codingchallenge.controller.ControllerExceptionHandler;
import com.heycar.codingchallenge.controller.UploadController;
import com.heycar.codingchallenge.repository.CarListingRepository;
import com.heycar.integrationTest.util.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HeyCarApplication.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class SearchListingTest {

    MockMvc mvc;
    MockMvc mvc2;

    @Autowired
    CarListingRepository carListingRepository;

    @Autowired
    CarListingSearchController carListingSearchController;

    @Autowired
    UploadController uploadController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(carListingSearchController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
        MockitoAnnotations.initMocks(this);
        mvc2 = MockMvcBuilders.standaloneSetup(uploadController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void searchWithAllParamTest() throws Exception {
        createJsonApiRequest(FileUtils.getResourceFileAsString("json/file1.json"));
        ResultActions resultActions = this.mvc.perform(get("/search?")
                .param("make", "Skoda")
                .param("model", "fabia"))
                .andDo(print())
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();

        String contentAsString = result.getResponse().getContentAsString();
        assertEquals(true, StringUtils.isNotEmpty(contentAsString));

    }

    private void createJsonApiRequest(String data) throws Exception {
        this.mvc2.perform(post("/vehicle_listing/1234")
                .contentType(MediaType.APPLICATION_JSON)
                .content(data))
                .andExpect(status().isOk());
    }
}
