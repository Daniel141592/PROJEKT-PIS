package pis.projekt.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pis.projekt.models.Magazine;
import pis.projekt.models.Section;
import pis.projekt.services.MagazineService;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(MagazineController.class)
@RunWith(SpringRunner.class)
public class MagazineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MagazineService magazineService;

    @InjectMocks
    private MagazineController magazineController;

    final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getAllMagazinesTest() throws Exception{
        List<Section> sections = new ArrayList<>();
        List<Magazine> magazines = new LinkedList<>();
        for (int i = 0; i < 6; ++i){
            magazines.add(new Magazine(i, "name", 21, 37, sections));
        }
        when(magazineService.findAllMagazines()).thenReturn(magazines);

        RequestBuilder req = MockMvcRequestBuilders.get("/magazines/all");
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        List<Magazine> actMagazines = objectMapper.readValue(response, new TypeReference<List<Magazine>>() {});

        Assertions.assertThat(actMagazines).isNotEmpty();
        Assertions.assertThat(actMagazines.size()).isEqualTo(6);
        Assertions.assertThat(actMagazines.get(5).getId()).isEqualTo(5);
    }

    @Test
    public void getMagazineByIdTest() throws Exception{
        List<Section> sections = new ArrayList<>();
        Magazine magazine = new Magazine(5, "name", 21, 37, sections);

        when(magazineService.findMagazineById(5)).thenReturn(magazine);

        RequestBuilder req = MockMvcRequestBuilders.get("/magazines/5");
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        Magazine actMagazine = objectMapper.readValue(response, new TypeReference<Magazine>() {});

        Assertions.assertThat(actMagazine.getId()).isEqualTo(5);
        Assertions.assertThat(actMagazine.getLength()).isEqualTo(21);

        req = MockMvcRequestBuilders.get("/magazines/10");
        response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();

        Assertions.assertThat(response).isEmpty();
    }

    @Test
    public void getMagazineByNameTest() throws Exception{
        List<Section> sections = new ArrayList<>();
        List<Magazine> magazines = new ArrayList<>();
        for (int i = 0; i < 6; ++i){
            magazines.add(new Magazine(i, "stehauf", 21, 37, sections));
        }
        when(magazineService.findMagazineByName(any())).thenReturn(magazines);

        RequestBuilder req = MockMvcRequestBuilders.get("/magazines/find?name=stehauf");
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        List<Magazine> actMagazines = objectMapper.readValue(response, new TypeReference<List<Magazine>>() {});

        Assertions.assertThat(actMagazines.size()).isEqualTo(6);
        Assertions.assertThat(actMagazines.get(5).getName()).isEqualTo("stehauf");
    }

    @Test
    public void addMagazineTest() throws Exception{
        List<Section> sections = new ArrayList<>();
        Magazine magazine = new Magazine(9, "stehauf", 21, 37, sections);
        when(magazineService.addMagazine(any())).thenReturn(magazine);

        RequestBuilder req = MockMvcRequestBuilders
                .post("/magazines/add")
                .content(objectMapper.writeValueAsString(magazine))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        Magazine actMagazine = objectMapper.readValue(response, new TypeReference<Magazine>() {});

        Assertions.assertThat(actMagazine.getId()).isEqualTo(9);
        Assertions.assertThat(actMagazine.getName()).isEqualTo("stehauf");
    }

    @Test
    public void deleteMagazineTest() throws Exception{
        when(magazineService.deleteMagazine(2)).thenReturn(true);
        when(magazineService.deleteMagazine(3)).thenReturn(false);

        RequestBuilder req = MockMvcRequestBuilders
                .post("/magazines/delete/2")
                .content(objectMapper.writeValueAsString(2));
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        boolean result = objectMapper.readValue(response, new TypeReference<Boolean>() {});
        Assertions.assertThat(result).isTrue();

        req = MockMvcRequestBuilders
                .post("/magazines/delete/3")
                .content(objectMapper.writeValueAsString(3));
        response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();

        boolean result2 = objectMapper.readValue(response, new TypeReference<Boolean>() {});
        Assertions.assertThat(result2).isFalse();
    }


//    @Test
//    public void createAndDownloadReportTest() throws Exception{
//        List<Section> sections = new ArrayList<>();
//        Magazine magazine = new Magazine(9, "stehauf", 21, 37, sections);
//        when(magazineService.findMagazineById(9)).thenReturn(magazine);
//
//        RequestBuilder req = MockMvcRequestBuilders.get("/magazines/report/9");
////        try {
//            String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
////        }
//        ResponseEntity actual = objectMapper.readValue(response, new TypeReference<ResponseEntity>() {});
//
//        Assertions.assertThat(actual).isNotNull();
//
//    }
}
