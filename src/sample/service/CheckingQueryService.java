package sample.service;

import sample.entity.task.Task;
import sample.service.impl.StudentServiceImpl;
import sample.utils.db.disconnection.DisconnectionUtil;

import java.sql.SQLException;

public interface CheckingQueryService {

    DisconnectionUtil disconnectionUtil = new DisconnectionUtil();

    StudentService studentService = new StudentServiceImpl();

    String checkQuery(String query, String url, Task task) throws SQLException;
}