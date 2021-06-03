package com.heycar.integrationTest;

import com.heycar.codingchallenge.HeyCarApplication;
import com.heycar.codingchallenge.controller.ControllerExceptionHandler;
import com.heycar.codingchallenge.controller.UploadController;
import com.heycar.codingchallenge.model.CarListing;
import com.heycar.codingchallenge.repository.CarListingRepository;
import com.heycar.integrationTest.util.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = HeyCarApplication.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class UploadListingApiIT {

    MockMvc mvc;
    MockMvc mvc2;
    @Autowired
    CarListingRepository carListingRepository;

    @Autowired
    UploadController uploadController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(uploadController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();

    }
    private void resetTable(){
        carListingRepository.deleteAll();
    }

    @Test
    public void uploadJsonListTest() throws Exception {
        createJsonApiRequest(FileUtils.getResourceFileAsString("json/file1.json"));
        List<CarListing> carListingList = carListingRepository.findAll();
        assertEquals(2, carListingList.size());
        assertEquals(java.util.Optional.of(9000.0).get(), carListingList.get(1).getPrice());
        // Second request with repeated listings
        createJsonApiRequest(FileUtils.getResourceFileAsString("json/file2.json"));
        carListingList = carListingRepository.findAll();
        assertEquals(3, carListingList.size());
        resetTable();


    }

    @Test
    public void uploadJsonListWithUpdateValuesTest() throws Exception {
        createJsonApiRequest(FileUtils.getResourceFileAsString("json/file1.json"));
        List<CarListing> carListingList = carListingRepository.findAll();
        assertEquals(2, carListingList.size());
        assertEquals(java.util.Optional.of(9000.0).get(),
                java.util.Optional.ofNullable(carListingList.get(1).getPrice()).get());
        // Second request with repeated listings

        createJsonApiRequest(FileUtils.getResourceFileAsString("json/file3.json"));
        List<CarListing> carListingList2 = carListingRepository.findAll();
        assertEquals(2, carListingList2.size());
        assertEquals(java.util.Optional.of(7000.0).get(),
                java.util.Optional.ofNullable(carListingList2.get(1).getPrice()).get());
        resetTable();


    }

    @Test
    public void uploadDealerCsvTest() throws Exception {
        getMultiPartFile("csv/csvlist1.csv");
        this.mvc.perform(
                multipart(String.format("/upload_csv/5678")).file(getMultiPartFile("csv/csvlist1.csv")))
                .andExpect(status().isOk());
        List<CarListing> carListingList = carListingRepository.findAll();
        assertEquals(2, carListingList.size());
    }

    @Test
    public void uploadMalformedCsvTest() throws Exception {
        this.mvc.perform(
                multipart(String.format("/upload_csv/5678")).file(getMultiPartFile("csv/csvlist2.csv")))
                .andExpect(status().isUnprocessableEntity());

    }

    private MockMultipartFile getMultiPartFile(String filePath) throws Exception {
        byte[] bytes =FileUtils.getResourceFileAsString(filePath).getBytes();
        return new MockMultipartFile("csvFile", "csvlist1.csv",
                MediaType.MULTIPART_FORM_DATA_VALUE, bytes);

    }

    private void createJsonApiRequest(String data) throws Exception {
        this.mvc.perform(post("/vehicle_listing/1234")
                .contentType(MediaType.APPLICATION_JSON)
                .content(data))
                .andExpect(status().isOk());
    }

}
