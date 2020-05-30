package sample.service;

import javafx.scene.control.TabPane;
import sample.service.impl.TeacherServiceImpl;
import sample.utils.db.disconnection.DisconnectionUtil;

public interface StatisticService {

    DisconnectionUtil disconnectionUtil = new DisconnectionUtil();

    TeacherService teacherService = new TeacherServiceImpl();

    void initializeGroups(TabPane tasks);
}
