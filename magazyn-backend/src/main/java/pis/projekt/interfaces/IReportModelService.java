package pis.projekt.interfaces;

import pis.projekt.models.ReportModel;

import java.util.List;

public interface IReportModelService {
    ReportModel findReportModelById(Integer reportModelId);

    List<ReportModel> findAllReportModels();

    public ReportModel addReportModel(ReportModel reportModel);
}
