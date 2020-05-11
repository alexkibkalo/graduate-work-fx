package sample.service;

import sample.entity.task.Task;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface StudentService {

    boolean attachStudentToLaboratoryWork(Long studentId, Long moduleId, Long visibleQuery);

    boolean hasStudentLaboratoryWork(Long userId, Long labId);

    void finishLab(Long userId, Long labId);

    List<Task> findAllQueriesByLabId(Long labId);

    Map<Integer, List<String>> executeTrueQuery(String query, String url) throws SQLException;
}
