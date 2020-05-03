package sample.entity.task;

public class Task {

    private Long id;

    private String description;

    private String trueQuery;

    public Task() {
    }

    public Task(Long id, String description, String trueQuery) {
        this.id = id;
        this.description = description;
        this.trueQuery = trueQuery;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrueQuery() {
        return trueQuery;
    }

    public void setTrueQuery(String trueQuery) {
        this.trueQuery = trueQuery;
    }
}
