package sample.service;

import javafx.scene.control.TabPane;
import sample.service.impl.TeacherServiceImpl;

public interface StatisticService {

    TeacherService teacherService = new TeacherServiceImpl();

    void initializeGroups(TabPane tasks);
}
