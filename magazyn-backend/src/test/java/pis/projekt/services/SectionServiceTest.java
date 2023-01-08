package pis.projekt.services;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pis.projekt.models.Magazine;
import pis.projekt.models.Product;
import pis.projekt.models.Section;
import pis.projekt.repository.ISectionRepository;
import pis.projekt.services.SectionService;
import pis.projekt.utils.Pair;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SectionServiceTest {

    @Mock
    ISectionRepository SR;

    @InjectMocks
    SectionService SService;

    @Test
    public void findAllsectionsTest(){
        List<Section> sections = new ArrayList<>();
        for (int i=0; i<6; i++){
            sections.add(new Section());
        }

        when(SR.findAll()).thenReturn(sections);
        Assertions.assertThat(SService.findAllSections()).isSameAs(sections);
    }

    @Test
    public void findSectionByIdTest(){
        Section sec = new Section();

        when(SR.findSectionById(5)).thenReturn(sec);
        Assertions.assertThat(SService.findSectionById(5)).isSameAs(sec);
        Assertions.assertThat(SService.findSectionById(27)).isNull();
    }

    @Test
    public void findSectionsByMagazine_IdTest(){
        List<Section> sections = new ArrayList<>();
        for (int i=0; i<6; i++){
            sections.add(new Section());
        }

        when(SR.findSectionsByMagazine_Id(2)).thenReturn(sections);
        Assertions.assertThat(SService.findSectionsByMagazine_Id(2)).isSameAs(sections);
        Assertions.assertThat(SService.findSectionsByMagazine_Id(10)).isEmpty();
    }

    @Test
    public void findSectionsByProduct_IdTest(){
        List<Section> sections = new ArrayList<>();
        for (int i=0; i<6; i++){
            sections.add(new Section());
        }

        when(SR.findSectionsByProduct_Id(4)).thenReturn(sections);
        Assertions.assertThat(SService.findSectionsByProduct_Id(4)).isSameAs(sections);
        Assertions.assertThat(SService.findSectionsByProduct_Id(10)).isEmpty();
    }

    @Test
    public void addSectionFailTest(){
        Section sect1 = new Section(new Magazine(2), null, null, 1, 3, 4, 0, 0);
        Section sect2 = new Section(new Magazine(2), null, null, 23, 3, 4, 1, 1);
        List<Section> secs = new ArrayList<>();
        secs.add(sect1);
        Magazine mag = new Magazine(0, "name", 20, 30, secs);

        when(SR.findSectionsByMagazine_Id(anyInt())).thenReturn(secs);
        Assertions.assertThat(SService.addSection(sect2)).isNull();
    }

    @Test
    public void addSectionSuccessTest(){
        Section sect1 = new Section(new Magazine(2), null, null, 1, 3, 4, 0, 0);
        Section sect2 = new Section(new Magazine(2), null, null, 2, 3, 4, 10, 11);
        List<Section> secs = new ArrayList<>();
        secs.add(sect1);
        Magazine mag = new Magazine(2, "name", 30, 30, secs);
        when(SR.save(sect2)).thenReturn(sect2);
        when(SR.findSectionsByMagazine_Id(2)).thenReturn(secs);
        Assertions.assertThat(SService.addSection(sect2).getAmount()).isEqualTo(2);
    }

    @Test
    public void deleteSectionFailTest(){
        when(SR.existsById(anyInt())).thenReturn(false);
        Assertions.assertThat(SService.deleteSection(5)).isFalse();
    }

    @Test
    public void deleteSectionSuccessTest(){
        when(SR.existsById(anyInt())).thenReturn(true);
        Assertions.assertThat(SService.deleteSection(20)).isTrue();
    }

    @Test
    public void checkCollisionTestTrue1(){
        Section sect1 = new Section(new Magazine(2), null, null, 1, 3, 4, 0, 0);
        Section sect2 = new Section(new Magazine(2), null, null, 2, 3, 4, 2, 2);
        List<Section> secs = new ArrayList<>();
        secs.add(sect1);
        Magazine mag = new Magazine(2, "name", 30, 30, secs);

        when(SR.findSectionsByMagazine_Id(anyInt())).thenReturn(secs);
        Assertions.assertThat(SService.checkCollision(2, sect2)).isTrue();
    }

    @Test
    public void checkCollisionTestTrue2(){
        Section sect1 = new Section(new Magazine(2), null, null, 1, 3, 4, 0, 0);
        Section sect2 = new Section(new Magazine(2), null, null, 2, 3, 4, 3, 4);
        List<Section> secs = new ArrayList<>();
        secs.add(sect1);
        Magazine mag = new Magazine(2, "name", 30, 30, secs);

        when(SR.findSectionsByMagazine_Id(anyInt())).thenReturn(secs);
        Assertions.assertThat(SService.checkCollision(2, sect2)).isTrue();
    }

    @Test
    public void checkCollisionTestFalse(){
        Section sect1 = new Section(new Magazine(2), null, null, 1, 3, 4, 0, 0);
        Section sect2 = new Section(new Magazine(2), null, null, 2, 3, 4, 5, 4);
        List<Section> secs = new ArrayList<>();
        secs.add(sect1);
        Magazine mag = new Magazine(2, "name", 30, 30, secs);

        when(SR.findSectionsByMagazine_Id(anyInt())).thenReturn(secs);
        Assertions.assertThat(SService.checkCollision(2, sect2)).isFalse();
    }

    @Test
    public void containsPointTestTrue1(){
        Section sect = new Section(null, null, null, 2, 3, 4, 2, 3);
        Pair pair = new Pair(4, 4);

        Assertions.assertThat(SectionService.containsPoint(sect, pair)).isTrue();
    }

    @Test
    public void constainsPointTestTrue2(){
        Section sect = new Section(null, null, null, 2, 3, 4, 2, 3);
        Pair pair = new Pair(2, 3);

        Assertions.assertThat(SectionService.containsPoint(sect, pair)).isTrue();
    }

    @Test
    public void constainsPointTestTrue3(){
        Section sect = new Section(null, null, null, 2, 3, 4, 2, 3);
        Pair pair = new Pair(4, 5);

        Assertions.assertThat(SectionService.containsPoint(sect, pair)).isTrue();
    }

    @Test
    public void constainsPointTestFalse1(){
        Section sect = new Section(null, null, null, 2, 3, 4, 2, 3);
        Pair pair = new Pair(1, 2);

        Assertions.assertThat(SectionService.containsPoint(sect, pair)).isFalse();
    }

    @Test
    public void constainsPointTestFalse2(){
        Section sect = new Section(null, null, null, 2, 3, 4, 2, 3);
        Pair pair = new Pair(10, 20);

        Assertions.assertThat(SectionService.containsPoint(sect, pair)).isFalse();
    }

    @Test
    public void addProductFail1Test(){
        Product prod = new Product("prodname", 2, 3, 5);
        Section sect = new Section(null, prod, "sectname", 4, 4, 4, 0, 0);
        when(SR.findSectionById(anyInt())).thenReturn(sect);
        Assertions.assertThat(SService.addProduct(80, 2)).isFalse();
    }

    @Test
    public void addProductFail2Test(){
        Product prod = new Product("prodname", 2, 3, 5);
        Section sect = new Section(null, prod, "sectname", 5, 20, 20, 0, 0);
        when(SR.findSectionById(anyInt())).thenReturn(sect);
        Assertions.assertThat(SService.addProduct(80, 2)).isFalse();
    }

    @Test
    public void addProductSuccessTest(){
        Product prod = new Product("prodname", 2, 3, 10);
        Section sect = new Section(null, prod, "sectname", 5, 20, 20, 0, 0);
        when(SR.findSectionById(anyInt())).thenReturn(sect);
        Assertions.assertThat(SService.addProduct(80, 2)).isTrue();
    }

    @Test
    public void calcAreaTest(){
        Section sect = new Section(null, null, "sectname", 5, 20, 20, 0, 0);
        Assertions.assertThat(SectionService.calcArea(sect)).isEqualTo(400);

    }
}
