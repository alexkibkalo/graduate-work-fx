package sample.service;

public interface NotificationService {

    void showDatabaseSuccessfullyCreated();

    void showQuerySuccessfullyCreated();

    void showStudentSuccessfullyCreated();

    void showModuleSuccessfullyCreated();

    void showThemeSuccessfullyCreated();

    void showStudentGroupSuccessfullyCreated();

    void showStatisticUpdated();

    void showDatabaseAlreadyExist();

    void showModuleAlreadyExist();

    void showThemeAlreadyExist();

    void showQueryAlreadyExist();

    void showGroupAlreadyExist();

    void showStudentAlreadyExist();

    void showSomethingWentWrong();
}
