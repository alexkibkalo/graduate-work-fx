package sample.service;

import java.util.List;

public interface TeacherService {

    ////// Insert operations ///////

    boolean addStudentGroup(String name);

    boolean addStudent(String name, String surname, String login, String password, Long groupId);

    boolean addModule(String name, Long queriesPerLab);

    boolean addDatabase(String name, String connectionURL);

    boolean addQuery(String name, String description, Long moduleId, Long databaseId);

    ///// Find operations //////

    List<String> findAllStudentGroups();

    List<String> findAllModules();

    List<String> findAllNotFinishedModules(Long userId);

    Long findIdByStudentGroupName(String name);

    Long findIdByModuleName(String name);

    List<String> findAllDatabases();

    Long findIdByDatabaseName(String name);

    Long findIdByStudentName(String name);

    boolean updateStudentStatistic();

    boolean validateExistsDatabase(String databaseURL);

    Long findCountVisibleQueriesByModuleId(Long idByModuleName);
}
