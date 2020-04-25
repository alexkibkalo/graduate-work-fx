package sample.entity.student;

public class ActiveUser {

    public String username;

    private ActiveUser() { }

    private static ActiveUser activeUser = new ActiveUser();

    public static ActiveUser getActiveUser() {
        return activeUser;
    }

    public static void setActiveUserFields(String username){
        activeUser.username = username;
    }
}
