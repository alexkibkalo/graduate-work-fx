package sample.entity.student;

public class Student {

    private Long id;

    private String name;

    private String surname;

    private String groupName;

    private Double status;

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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Double getStatus() {
        return status;
    }

    public void setStatus(Double status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.name + " "
                + this.surname + " "
                + this.groupName + " "
                + this.status;
    }
}
