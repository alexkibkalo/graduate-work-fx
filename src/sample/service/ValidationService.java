package sample.service;

public interface ValidationService {

    boolean authentication(String login, String password, boolean isTeacher);

    boolean isEmptyFields(String login, String password);

    boolean studentGroupAlreadyExist(String groupName);

    boolean studentAlreadyExist(String login);

    boolean databaseAlreadyExist(String name);

    boolean moduleAlreadyExist(String name);

    boolean queryAlreadyExist(String name);

    boolean queryValid(String name, Long databaseId);
}
