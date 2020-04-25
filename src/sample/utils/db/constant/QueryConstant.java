package sample.utils.db.constant;

public class QueryConstant {

    ///////// TEACHER ///////////
    public final static String SELECT_TEACHER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM teacher WHERE login = ? AND password = ?";

    ///////// GROUP  ////////////
    public final static String CREATE_NEW_STUDENT_GROUP = "INSERT INTO student_group (name) VALUES (?)";
    public final static String SELECT_STUDENT_GROUP_BY_NAME = "SELECT * FROM student_group WHERE name = ?";
    public final static String SELECT_ALL_STUDENT_GROUPS = "SELECT * FROM student_group";

    ///////// STUDENT ///////////
    public final static String SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM student WHERE login = ? AND password = ?";
    public final static String SELECT_STUDENT_BY_NAME = "SELECT * FROM student WHERE login = ?";
    public final static String CREATE_NEW_STUDENT = "INSERT INTO student (name, surname, login, password, status, group_id) VALUES (?, ?, ?, ?, ?, ?)";

    ///////// DATABASE //////////
    public final static String SELECT_ALL_DATABASES = "SELECT * FROM database";
    public final static String CREATE_NEW_DATABASE = "INSERT INTO database (name, url) VALUES (?, ?)";
    public final static String SELECT_DATABASE_BY_NAME = "SELECT * FROM database WHERE name = ?";

    ///////// THEME /////////////
    public final static String SELECT_ALL_THEMES = "SELECT * FROM theme";
    public final static String CREATE_NEW_THEME = "INSERT INTO theme (name, module_id) VALUES (?, ?)";
    public final static String SELECT_THEME_BY_NAME = "SELECT * FROM theme WHERE name = ?";

    ///////// QUERY /////////////
    public final static String CREATE_NEW_QUERY = "INSERT INTO query (name, description, module_id, database_id) VALUES (?, ?, ?, ?)";
    public final static String SELECT_QUERY_BY_NAME = "SELECT * FROM query WHERE name = ?";

    ///////// MODULE ////////////
    public final static String SELECT_ALL_MODULES = "SELECT * FROM module";
    public final static String SELECT_ALL_NOT_FINISHED_MODULES = "SELECT * FROM module m left join student_to_lab stl ON m.id = stl.lab_id and student_id = ? where stl.is_finished = 0 or stl.is_finished is null";
    public final static String CREATE_NEW_MODULE = "INSERT INTO module (name) VALUES (?)";
    public final static String SELECT_MODULE_BY_NAME = "SELECT * FROM module WHERE name = ?";

    ////////// JOIN ////////////
    public final static String CREATE_NEW_JOIN = "INSERT INTO student_to_lab (student_id, lab_id, visible_query, is_finished) VALUES (?, ?, ?, ?)";
    public final static String SELECT_JOIN_BY_USER_ID_AND_LAB_ID = "SELECT * FROM student_to_lab WHERE student_id = ? and lab_id = ?";

}
