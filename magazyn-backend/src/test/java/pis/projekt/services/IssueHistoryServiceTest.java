package pis.projekt.services;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pis.projekt.models.Issue;
import pis.projekt.models.IssueHistory;
import pis.projekt.repository.IIssueHistoryRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IssueHistoryServiceTest {

    @Mock
    IIssueHistoryRepository IHR;

    @InjectMocks
    IssueHistoryService IHService;

    @Test
    public void findAllIssueHistoriesTest(){
        List<IssueHistory> ihs = new ArrayList<>();
        for (int i=0; i<10; ++i) {
            ihs.add(new IssueHistory());
        }

        when(IHR.findAll()).thenReturn(ihs);
        Assertions.assertThat(IHService.findAllIssueHistories()).isSameAs(ihs);
        Assertions.assertThat(IHService.findAllIssueHistories().size()).isEqualTo(10);
    }

    @Test
    public void findIssueHistoryByIdTest(){
        IssueHistory ihs = new IssueHistory();

        when(IHR.findIssueHistoryById(1)).thenReturn(ihs);
        Assertions.assertThat(IHService.findIssueHistoryById(1)).isSameAs(ihs);
        Assertions.assertThat(IHService.findIssueHistoryById(109)).isNull();
    }


    @Test
    public void findIssueHistoriesByIssuingManager_IdTest(){
        List<IssueHistory> ihs = new ArrayList<>();
        for (int i=0; i<10; ++i) {
            ihs.add(new IssueHistory());
        }

        when(IHR.findIssueHistoriesByIssuingManager_Id(2)).thenReturn(ihs);
        Assertions.assertThat(IHService.findIssueHistoriesByIssuingManager_Id(2)).isSameAs(ihs);
        Assertions.assertThat(IHService.findIssueHistoriesByIssuingManager_Id(2137)).isEmpty();
    }

    @Test
    public void findIssueHistoriesByIssuedEmployee_IdTest(){
        List<IssueHistory> ihs = new ArrayList<>();
        for (int i=0; i<10; ++i) {
            ihs.add(new IssueHistory());
        }

        when(IHR.findIssueHistoriesByIssuedEmployee_Id(2)).thenReturn(ihs);
        Assertions.assertThat(IHService.findIssueHistoriesByIssuedEmployee_Id(2)).isSameAs(ihs);
        Assertions.assertThat(IHService.findIssueHistoriesByIssuedEmployee_Id(2137)).isEmpty();
    }

    @Test
    public void findIssueHistoriesByDescriptionContainsTest(){
        List<IssueHistory> ihs = new ArrayList<>();
        for (int i=0; i<10; ++i) {
            ihs.add(new IssueHistory());
        }

        when(IHR.findIssueHistoriesByDescriptionContains("dupa")).thenReturn(ihs);
        Assertions.assertThat(IHService.findIssueHistoriesByDescriptionContains("dupa")).isSameAs(ihs);
        Assertions.assertThat(IHService.findIssueHistoriesByDescriptionContains("notdupa")).isEmpty();
    }

    @Test
    public void findIssueHistoriesByModifyDateTest(){
        List<IssueHistory> ihs = new ArrayList<>();
        for (int i=0; i<10; ++i) {
            ihs.add(new IssueHistory());
        }

        Timestamp stamp = new Timestamp(2022);

        when(IHR.findIssueHistoriesByModifyDate(stamp)).thenReturn(ihs);
        Assertions.assertThat(IHService.findIssueHistoriesByModifyDate(stamp)).isSameAs(ihs);
        Assertions.assertThat(IHService.findIssueHistoriesByModifyDate(new Timestamp(2000))).isEmpty();
    }

    @Test
    public void deleteIssueHistoryFailTest(){
        when(IHR.existsById(20)).thenReturn(false);
        Assertions.assertThat(IHService.deleteIssueHistory(20)).isFalse();
    }

    @Test
    public void deleteIssueHistorySuccessTest(){
        when(IHR.existsById(5)).thenReturn(true);
        Assertions.assertThat(IHService.deleteIssueHistory(5)).isTrue();
    }

}
