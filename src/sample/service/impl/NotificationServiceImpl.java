package sample.service.impl;

import javafx.scene.control.Alert;
import sample.service.NotificationService;

public class NotificationServiceImpl implements NotificationService {

    ///////////////////     INFORMATION     ////////////////////

    @Override
    public void showDatabaseSuccessfullyCreated(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Create DATABASE");
        alert.setContentText("Database successfully created!");
        alert.showAndWait();
    }

    @Override
    public void showQuerySuccessfullyCreated(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Create QUERY");
        alert.setContentText("Query successfully created!");
        alert.showAndWait();
    }

    @Override
    public void showStudentSuccessfullyCreated(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Create STUDENT");
        alert.setContentText("Student successfully created!");
        alert.showAndWait();
    }

    @Override
    public void showModuleSuccessfullyCreated(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Create MODULE");
        alert.setContentText("Module successfully created!");
        alert.showAndWait();
    }

    @Override
    public void showStudentGroupSuccessfullyCreated(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Create Student Group");
        alert.setContentText("Student Group successfully created!");
        alert.showAndWait();
    }

    ///////////////////     WARNING     ////////////////////

    @Override
    public void showDatabaseAlreadyExist(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Create Database");
        alert.setContentText("Database already exist...\n");
        alert.showAndWait();
    }

    @Override
    public void showModuleAlreadyExist(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Create Module");
        alert.setContentText("Module already exist...\n");
        alert.showAndWait();
    }

    @Override
    public void showQueryAlreadyExist(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Create Query");
        alert.setContentText("Query already exist...\n");
        alert.showAndWait();
    }

    @Override
    public void showGroupAlreadyExist(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Create Group");
        alert.setContentText("Group already exist...\n");
        alert.showAndWait();
    }

    @Override
    public void showStudentAlreadyExist() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Create Student");
        alert.setContentText("Student already exist...\n");
        alert.showAndWait();
    }

    @Override
    public void showDatabaseDoesNotExist() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Database create");
        alert.setContentText("Database doesn't exist!");
        alert.showAndWait();
    }

    ///////////////////     ERROR     ////////////////////

    @Override
    public void showSomethingWentWrong(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Create DATABASE");
        alert.setContentText("Ups... Something went wrong...\n");
        alert.showAndWait();
    }

    @Override
    public void showQueryIsInvalid() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Create QUERY");
        alert.setContentText("Query is invalid...\n");
        alert.showAndWait();
    }
}
