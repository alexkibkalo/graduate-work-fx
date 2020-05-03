package sample.service;

import javafx.scene.control.TabPane;
import sample.entity.task.Task;

import java.sql.SQLException;
import java.util.List;

public interface StudentService {

    boolean attachStudentToLaboratoryWork(Long studentId, Long moduleId, Long visibleQuery);

    boolean hasStudentLaboratoryWork(Long userId, Long labId);

    String checkQuery(String query);

    List<String> executeTrueQuery(String query) throws SQLException;

    void finishLab(Long userId, Long labId);

    List<Task> findAllQueriesByLabId(Long labId);

    void initializeTasks(List<Task> allQueriesByLabId, TabPane tasks);
}
