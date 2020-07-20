package sample.utils.db.constant;

public class QueryConstant {

    ///////// TEACHER ///////////
    public final static String SELECT_TEACHER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM teacher WHERE login = ? AND password = ?";
    public final static String UPDATE_STUDENTS_STATISTIC = "call update_students_statistic()";

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
    public final static String SELECT_URL_BY_ID = "SELECT url FROM database WHERE id = ?";

    ///////// QUERY /////////////
    public final static String CREATE_NEW_QUERY = "INSERT INTO query (name, description, module_id, database_id) VALUES (?, ?, ?, ?)";
    public final static String SELECT_QUERY_BY_NAME = "SELECT * FROM query WHERE name = ?";
    public final static String SELECT_ALL_QUERIES_BY_LAB_ID = "SELECT * FROM query WHERE module_id = ?";
    public final static String SELECT_NUMBER_VISIBLE_QUERIES_FOR_MODULE = "SELECT visible_query FROM module WHERE id = ?";
    public final static String FINISH_LAB_WORK = "UPDATE student_to_lab SET is_finished = 1 where student_id = ? and lab_id = ?";

    ///////// MODULE ////////////
    public final static String SELECT_ALL_MODULES = "SELECT * FROM module";
    public final static String SELECT_ALL_NOT_FINISHED_MODULES = "SELECT * FROM module m left join student_to_lab stl ON m.id = stl.lab_id and student_id = ? where stl.is_finished = 0 or stl.is_finished is null";
    public final static String CREATE_NEW_MODULE = "INSERT INTO module (name, visible_query) VALUES (?, ?)";
    public final static String SELECT_MODULE_BY_NAME = "SELECT * FROM module WHERE name = ?";
    public final static String SELECT_VISIBLE_QUERIES_BY_MODULE_ID = "SELECT visible_query FROM module WHERE id = ?";
    public static final String SELECT_NUMBER_EXECUTED_QUERIES_FOR_MODULE = "select count(*) from result_table where query_id in (select query.id from query where module_id = ?)";

    ////////// JOIN ////////////
    public final static String CREATE_NEW_JOIN = "INSERT INTO student_to_lab (student_id, lab_id, is_finished) VALUES (?, ?, ?)";
    public final static String SELECT_JOIN_BY_USER_ID_AND_LAB_ID = "SELECT * FROM student_to_lab WHERE student_id = ? and lab_id = ?";

    //// Executing Lab Work ////
    public final static String CREATE_STATUS_SUCCESS = "INSERT INTO result_table (stud_id, count_times, query_id, true_query) VALUES (?, ?, ?, ?)";
    public final static String EXISTS_BY_STUDENT_ID_AND_QUERY_ID = "SELECT * FROM result_table WHERE stud_id = ? and query_id = ?";

    /// POPULATION STATISTIC ///
    public final static String POPULATION_STATISTICS = "select s.id, s.name as sname, s.surname, s.login, sg.name as sgname, s.status from student s inner join student_group sg on s.group_id = sg.id";
    public final static String GET_COUNT_OF_TIMES = "select sum(count_times) common_count from result_table where stud_id = ?";
    public final static String GET_LABS_TO_EXECUTE = "select m.name from student_to_lab stl inner join module m on stl.lab_id = m.id where student_id = ? and is_finished = 0;";
}
