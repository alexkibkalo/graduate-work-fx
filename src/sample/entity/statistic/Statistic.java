package sample.entity.statistic;

public class Statistic {

    private Long id;

    private String name;

    private String surname;

    private String email;

    private Double status;

    private String groupName;

    private int countTimes;

    private String labToExecute;

    public Statistic(Long id, String name, String surname, String email, Double status, String groupName) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.status = status;
        this.groupName = groupName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getStatus() {
        return status;
    }

    public void setStatus(Double status) {
        this.status = status;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getCountTimes() {
        return countTimes;
    }

    public void setCountTimes(int countTimes) {
        this.countTimes = countTimes;
    }

    public String getLabToExecute() {
        return labToExecute;
    }

    public void setLabToExecute(String labToExecute) {
        this.labToExecute = labToExecute;
    }
}
