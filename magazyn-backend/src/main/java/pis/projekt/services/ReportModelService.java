package pis.projekt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pis.projekt.interfaces.IReportModelService;
import pis.projekt.models.Issue;
import pis.projekt.models.IssueHistory;
import pis.projekt.models.ReportModel;
import pis.projekt.repository.IReportModelRepository;

import java.util.List;

@Service
public class ReportModelService implements IReportModelService {
    @Autowired
    IReportModelRepository reportModelRepository;

    @Override
    public ReportModel findReportModelById(Integer reportModelId){return reportModelRepository.findReportModelById(reportModelId);}

    @Override
    public List<ReportModel> findAllReportModels(){return reportModelRepository.findAll();}

    @Override
    public ReportModel addReportModel(ReportModel reportModel) {
        return reportModelRepository.save(reportModel);
    }

}
