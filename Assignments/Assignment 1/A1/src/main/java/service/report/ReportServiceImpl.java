package service.report;

import model.Report;
import model.validation.Notification;
import repository.report.ReportRepository;

import java.sql.Date;
import java.util.List;

public class ReportServiceImpl implements ReportService{

    private ReportRepository reportRepository;

    public ReportServiceImpl (ReportRepository reportRepository)
    {
        this.reportRepository = reportRepository;
    }

    @Override
    public boolean create(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }
}
