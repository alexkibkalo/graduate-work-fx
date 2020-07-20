package sample.service;

public interface NotificationService {

    void showDatabaseSuccessfullyCreated();

    void showQuerySuccessfullyCreated();

    void showStudentSuccessfullyCreated();

    void showModuleSuccessfullyCreated();

    void showStudentGroupSuccessfullyCreated();

    void showDatabaseAlreadyExist();

    void showModuleAlreadyExist();

    void showQueryAlreadyExist();

    void showGroupAlreadyExist();

    void showStudentAlreadyExist();

    void showSomethingWentWrong();

    void showDatabaseDoesNotExist();

    void showQueryIsInvalid();
}
