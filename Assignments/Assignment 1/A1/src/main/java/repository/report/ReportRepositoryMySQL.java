package repository.report;

import model.Report;
import model.builder.ReportBuilder;
import model.validation.Notification;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static database.Constants.Tables.CLIENT;
import static database.Constants.Tables.REPORT;

public class ReportRepositoryMySQL implements ReportRepository {
    private final Connection connection;
    public ReportRepositoryMySQL (Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public List<Report> findAll() {
        List <Report> reportList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String fetchClientSql = "SELECT * FROM `" + REPORT + "`";
            ResultSet reportResult = statement.executeQuery(fetchClientSql);
            if (reportResult.next())
            {
                Report report = new ReportBuilder()
                        .setId(reportResult.getInt("id"))
                        .setIdEmployee(reportResult.getInt("idEmployee"))
                        .setDate(reportResult.getDate("date"))
                        .setActivity(reportResult.getString("activity"))
                        .build();
                reportList.add(report);
            }
            return reportList;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Report report) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO `"+REPORT+"` values(null,?,?,?)");
            insertStatement.setInt(1, report.getIdEmployee());
            insertStatement.setDate(2, (java.sql.Date) report.getDate());
            insertStatement.setString(3, report.getActivity());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

