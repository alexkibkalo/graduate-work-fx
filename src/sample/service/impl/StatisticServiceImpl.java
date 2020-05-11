package sample.service.impl;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.entity.student.Student;
import sample.service.StatisticService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticServiceImpl implements StatisticService {

    @Override
    public void initializeGroups(TabPane tabPane) {
        int index = 1;
        List<String> groups = teacherService.findAllStudentGroups();
        Collections.sort(groups);

        for (String groupName : groups) {
            Tab tab = new Tab();
            tab.setText(groupName);

            VBox content = new VBox();

            content.getChildren().add(new Label());
            content.getChildren().add(subContent1(groupName));

            tab.setContent(content);
            tabPane.getTabs().add(tab);

            index += 1;
        }
    }

    public HBox subContent1(String groupName) {
        HBox subContent = new HBox();

        subContent.getChildren().add(new Label());
        ListView<String> students = new ListView<>();
        List<String> studentToList = convertToListStringStudents(
                teacherService.findAllStudents().stream()
                        .filter(student -> student.getGroupName().equals(groupName))
                        .collect(Collectors.toList())
        );

        students.getItems().addAll(studentToList);

        subContent.getChildren().add(students);
        subContent.setSpacing(15);

        return subContent;
    }

    private List<String> convertToListStringStudents(List<Student> students) {
        List<String> list = new ArrayList<>();
        for (Student student : students) {
            list.add(student.toString());
        }
        return list;
    }
}
