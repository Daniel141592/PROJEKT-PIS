package pis.projekt.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pis.projekt.models.Magazine;
import pis.projekt.models.Product;
import pis.projekt.models.Section;
import pis.projekt.models.requests.AddProductRequest;
import pis.projekt.security.JwtTokenFilter;
import pis.projekt.services.SectionService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(SectionController.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class SectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SectionService sectionService;

    @MockBean
    private JwtTokenFilter jwtTokenFilter;

    final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getAllSectionsTest() throws Exception{
        Magazine mag = new Magazine();
        Product prod = new Product();
        List<Section> sections = new LinkedList<>();
        for (int i=0; i<8; i++){
            sections.add(new Section(i, mag, prod, "name", i+1, i+2, i+3, i+4, i+5));
        }

        when(sectionService.findAllSections()).thenReturn(sections);
        RequestBuilder req = MockMvcRequestBuilders.get("/sections/all");
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        List<Section> actSections = objectMapper.readValue(response, new TypeReference<List<Section>>() {});
        Assertions.assertThat(actSections.size()).isEqualTo(8);
        Assertions.assertThat(actSections.get(7).getBottomLeftPointY()).isEqualTo(12);
    }

    @Test
    public void getSectionByIdTest() throws Exception{
        Magazine mag = new Magazine();
        Product prod = new Product();
        int i = 1;
        Section section = new Section(i, mag, prod, "name", i+1, i+2, i+3, i+4, i+5);
        when(sectionService.findSectionById(any())).thenReturn(section);
        RequestBuilder req = MockMvcRequestBuilders.get("/sections/1");
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        Section actSection = objectMapper.readValue(response, new TypeReference<Section>() {});

        Assertions.assertThat(actSection.getName()).isEqualTo("name");
        Assertions.assertThat(actSection.getBottomLeftPointX()).isEqualTo(5);
    }


    @Test
    public void getSectionsByUnitIdMagazineTest() throws Exception{
        Magazine mag = new Magazine();
        Product prod = new Product();
        List<Section> sections = new LinkedList<>();
        for (int i=0; i<8; i++){
            sections.add(new Section(i, mag, prod, "name", i+1, i+2, i+3, i+4, i+5));
        }
        when(sectionService.findSectionsByMagazine_Id(any())).thenReturn(sections);
        RequestBuilder req = MockMvcRequestBuilders.get("/sections/find?magazineid=1");
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        List<Section> actSections = objectMapper.readValue(response, new TypeReference<List<Section>>() {});
        Assertions.assertThat(actSections.size()).isEqualTo(8);
    }

    @Test
    public void getSectionsByUnitIdProductTest() throws Exception{
        Magazine mag = new Magazine();
        Product prod = new Product();
        List<Section> sections = new LinkedList<>();
        for (int i=0; i<8; i++){
            sections.add(new Section(i, mag, prod, "name", i+1, i+2, i+3, i+4, i+5));
        }
        when(sectionService.findSectionsByProduct_Id(any())).thenReturn(sections);
        RequestBuilder req = MockMvcRequestBuilders.get("/sections/find?productid=1");
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        List<Section> actSections = objectMapper.readValue(response, new TypeReference<List<Section>>() {});
        Assertions.assertThat(actSections.size()).isEqualTo(8);
        Assertions.assertThat(actSections.get(2).getId()).isEqualTo(2);
    }

    @Test
    public void getSectionsByUnitIdNullTest() throws Exception{
        when(sectionService.findSectionsByProduct_Id(any())).thenReturn(new ArrayList<>());
        RequestBuilder req = MockMvcRequestBuilders.get("/sections/find");
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        List<Section> actSections = objectMapper.readValue(response, new TypeReference<List<Section>>() {});
        Assertions.assertThat(actSections).isEmpty();
    }

    @Test
    public void addSectionTest() throws Exception{
        Magazine mag = new Magazine();
        Product prod = new Product();
        int i = 1;
        Section section = new Section(i, mag, prod, "name", i+1, i+2, i+3, i+4, i+5);
        when(sectionService.addSection(any())).thenReturn(section);

        RequestBuilder req = MockMvcRequestBuilders
                .post("/sections/add")
                .content(objectMapper.writeValueAsString(section))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        Section actSection = objectMapper.readValue(response, new TypeReference<Section>() {});
        Assertions.assertThat(actSection.getId()).isEqualTo(1);
        Assertions.assertThat(actSection.getName()).isEqualTo("name");
    }

    @Test
    public void deleteSectionTest() throws Exception{
        when(sectionService.deleteSection(2)).thenReturn(true);
        when(sectionService.deleteSection(3)).thenReturn(false);

        RequestBuilder req = MockMvcRequestBuilders
                .post("/sections/delete/2")
                .content(objectMapper.writeValueAsString(2));
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        boolean result = objectMapper.readValue(response, new TypeReference<Boolean>() {});
        Assertions.assertThat(result).isTrue();

        req = MockMvcRequestBuilders
                .post("/sections/delete/3")
                .content(objectMapper.writeValueAsString(3));
        response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        result = objectMapper.readValue(response, new TypeReference<Boolean>() {});
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void addProductTest() throws Exception{
        when(sectionService.addProduct(1, 1)).thenReturn(true);
        when(sectionService.addProduct(21, 37)).thenReturn(false);
        AddProductRequest goodRequest = new AddProductRequest(1, 1);
        AddProductRequest badRequest = new AddProductRequest(21, 37);

        RequestBuilder req = MockMvcRequestBuilders
                .post("/sections/product/add")
                .content(objectMapper.writeValueAsString(goodRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        String response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        boolean result = objectMapper.readValue(response, new TypeReference<Boolean>() {});
        Assertions.assertThat(result).isTrue();

        req = MockMvcRequestBuilders
                .post("/sections/product/add")
                .content(objectMapper.writeValueAsString(badRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        response = mockMvc.perform(req).andReturn().getResponse().getContentAsString();
        result = objectMapper.readValue(response, new TypeReference<Boolean>() {});
        Assertions.assertThat(result).isFalse();
    }

}
