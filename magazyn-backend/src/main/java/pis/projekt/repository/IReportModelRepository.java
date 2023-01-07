package pis.projekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pis.projekt.models.ReportModel;

public interface IReportModelRepository extends JpaRepository<ReportModel, Integer> {
    ReportModel findReportModelById(Integer reportModelId);
}
