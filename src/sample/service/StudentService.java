package sample.service;

import sample.entity.task.Task;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface StudentService {

    boolean attachStudentToLaboratoryWork(Long studentId, Long moduleId);

    boolean hasStudentLaboratoryWork(Long userId, Long labId);

    void finishLab(Long userId, Long labId);

    List<Task> findAllQueriesByLabId(Long labId);

    List<Task> findAllQueriesByLabIdAndStudentId(Long labId, Long studentId);

    Map<Integer, List<String>> executeTrueQuery(String query, String url) throws SQLException;

    int getNumberVisibleQueriesForModule(Long moduleId);

    int getNumberExecutedQueriesForModule(Long moduleId);
}
