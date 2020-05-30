package sample.service;

import sample.entity.student.Student;

import java.util.List;

public interface TeacherService {

    ////// Insert operations ///////

    boolean addStudentGroup(String name);

    boolean addStudent(String name, String surname, String login, String password, Long groupId);

    boolean addModule(String name);

    boolean addTheme(String name, Long moduleId);

    boolean addDatabase(String name, String connectionURL);

    boolean addQuery(String name, String description, Long moduleId, Long databaseId);

    ///// Find operations //////

    List<String> findAllStudentGroups();

    List<String> findAllModules();

    List<Student> findAllStudents();

    List<String> findAllNotFinishedModules(Long userId);

    Long findIdByStudentGroupName(String name);

    Long findIdByModuleName(String name);

    Long findIdByThemeName(String name);

    List<String> findAllThemes();

    List<String> findAllDatabases();

    Long findIdByDatabaseName(String name);

    Long findIdByStudentName(String name);

    boolean updateStudentStatistic();

    boolean validateExistsDatabase(String databaseURL);
}
