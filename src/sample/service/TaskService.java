package sample.service;

import javafx.scene.control.TabPane;
import sample.entity.task.Task;
import sample.service.impl.CheckingQueryServiceImpl;
import sample.service.impl.StudentServiceImpl;
import sample.service.impl.TeacherServiceImpl;
import sample.utils.db.disconnection.DisconnectionUtil;

import java.util.List;

public interface TaskService {

    DisconnectionUtil disconnectionUtil = new DisconnectionUtil();

    CheckingQueryService checkingQueryService = new CheckingQueryServiceImpl();

    StudentService studentService = new StudentServiceImpl();

    TeacherService teacherService = new TeacherServiceImpl();

    void initializeTasks(List<Task> list, TabPane tasks);

}
