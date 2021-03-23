package repository.report;

import model.Report;
import model.validation.Notification;

import java.util.Date;
import java.util.List;

public interface ReportRepository {

    List<Report> findAll();
    boolean save (Report report);


}
