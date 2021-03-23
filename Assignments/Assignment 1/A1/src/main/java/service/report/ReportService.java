package service.report;

import model.Report;
import model.validation.Notification;

import java.sql.Date;
import java.util.List;

public interface ReportService {

    boolean create (Report report);
    List<Report> findAll();
}
