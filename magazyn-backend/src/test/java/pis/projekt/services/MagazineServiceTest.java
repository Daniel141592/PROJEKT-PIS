package pis.projekt.services;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pis.projekt.models.Magazine;
import pis.projekt.models.Product;
import pis.projekt.models.Section;
import pis.projekt.repository.IMagazineRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MagazineServiceTest {

    @Mock
    IMagazineRepository MR;

    @InjectMocks
    MagazineService MService;

    @Test
    public void findAllMagazinesTest(){
        List<Magazine> magazines = new ArrayList<>();
        for (int i=0; i<10; ++i){
            magazines.add(new Magazine());
        }

        when(MR.findAll()).thenReturn(magazines);
        Assertions.assertThat(MService.findAllMagazines()).isSameAs(magazines);
        Assertions.assertThat(MService.findAllMagazines().size()).isEqualTo(10);
    }

    @Test
    public void findMagazineByIdTest(){
        Magazine magazine = new Magazine();

        when(MR.findMagazineById(2137)).thenReturn(magazine);
        Assertions.assertThat(MService.findMagazineById(2137)).isSameAs(magazine);
    }

    @Test
    public void findMagazineByNameTest(){
        List<Magazine> magazines = new ArrayList<>();
        for (int i=0; i<10; ++i){
            magazines.add(new Magazine());
        }

        when(MR.findMagazineByNameContaining("amen")).thenReturn(magazines);
        Assertions.assertThat(MService.findMagazineByName("amen")).isSameAs(magazines);
        Assertions.assertThat(MService.findMagazineByName("")).isEmpty();
    }

    @Test
    public void addMagazineTest(){
        Magazine magazine = new Magazine();

        when(MR.save(magazine)).thenReturn(magazine);
        Assertions.assertThat(MService.addMagazine(magazine)).isSameAs(magazine);
    }

    @Test
    public void deleteMagazineFailTest(){
        when(MR.existsById(999)).thenReturn(false);
        Assertions.assertThat(MService.deleteMagazine(999)).isFalse();
    }

    @Test
    public void deleteMagazineSuccessTest(){
        when(MR.existsById(0)).thenReturn(true);
        Assertions.assertThat(MService.deleteMagazine(0)).isTrue();
    }

    @Test
    public void calcSpaceTest()
    {
        Magazine mag = new Magazine(7, "benedict", 6, 9);
        Assertions.assertThat(MagazineService.calcSpace(mag)).isEqualTo(54);
    }

    @Test
    public void calcEmptySpaceTest()
    {
        Magazine mag = new Magazine(7, "benedict", 21, 37);

        Section sect1 = new Section(null, null, null, 5, 2, 3, 0, 0);
        Section sect2 = new Section(null, null, null, 5, 5, 6, 0, 0);
        Section sect3 = new Section(null, null, null, 5, 5, 10, 0, 0);

        List<Section> sections = new ArrayList<>();
        sections.add(sect1);
        sections.add(sect2);
        sections.add(sect3);
        mag.setSections(sections);

        Assertions.assertThat(MagazineService.calcEmptySpace(mag, false)).isEqualTo(691);
        Assertions.assertThat(MagazineService.calcEmptySpace(mag, true)).isCloseTo(88.93, Offset.offset(0.1));
    }

    @Test
    public void getProductAmountTest()
    {
        Magazine mag = new Magazine(7, "benedict", 210, 370);

        Product beer = new Product("beer", 3, 4, 4);
        Product notBeer = new Product("notbeer", 3, 4, 4);

        Section sect1 = new Section(null, beer, null, 2, 2, 3, 0, 0);
        Section sect2 = new Section(null, beer, null, 5, 5, 6, 0, 0);
        Section sect3 = new Section(null, notBeer, null, 5, 5, 10, 0, 0);

        List<Section> sections = new ArrayList<>();
        sections.add(sect1);
        sections.add(sect2);
        sections.add(sect3);
        mag.setSections(sections);

        Assert.assertEquals(7, MagazineService.getProductAmount(mag, beer));
        Assert.assertEquals(5, MagazineService.getProductAmount(mag, notBeer));
    }

    @Test
    public void getProductVectorTest(){
        Magazine mag = new Magazine(7, "benedict", 210, 370);

        Product beer = new Product("beer", 3, 4, 4);
        Product notBeer = new Product("notbeer", 3, 4, 4);

        Section sect1 = new Section(null, beer, null, 2, 2, 3, 0, 0);
        Section sect2 = new Section(null, beer, null, 5, 5, 6, 0, 0);
        Section sect3 = new Section(null, notBeer, null, 5, 5, 10, 0, 0);

        List<Section> sections = new ArrayList<>();
        sections.add(sect1);
        sections.add(sect2);
        sections.add(sect3);
        mag.setSections(sections);

        Vector<Product> products = new Vector<Product>();
        products.add(beer);
        products.add(notBeer);

        Assertions.assertThat(MagazineService.getProductVector(mag).get(0).getName()).isEqualTo("beer");
        Assertions.assertThat(MagazineService.getProductVector(mag).get(1).getName()).isEqualTo("notbeer");
    }

    @Test
    public void getProductSectionsTest(){
        Magazine mag = new Magazine(7, "benedict", 210, 370);

        Product beer = new Product("beer", 3, 4, 4);
        Product notBeer = new Product("notbeer", 3, 4, 4);

        Section sect1 = new Section(null, beer, null, 2, 2, 3, 0, 0);
        Section sect2 = new Section(null, beer, null, 5, 5, 6, 0, 0);
        Section sect3 = new Section(null, notBeer, null, 5, 5, 10, 0, 0);

        List<Section> sections = new ArrayList<>();
        sections.add(sect1);
        sections.add(sect2);
        sections.add(sect3);
        mag.setSections(sections);

        Assertions.assertThat(MagazineService.getProductSections(mag, beer)).isEqualTo(2);
        Assertions.assertThat(MagazineService.getProductSections(mag, notBeer)).isEqualTo(1);

    }

}
