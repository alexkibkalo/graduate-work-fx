package sample.entity.task;

public class Task {

    private Long id;

    private String description;

    private String trueQuery;

    private Long databaseId;

    private String status;

    public Task() {
    }

    public Task(Long id, String description, String trueQuery, Long databaseId) {
        this.id = id;
        this.description = description;
        this.trueQuery = trueQuery;
        this.databaseId = databaseId;
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

    public Long getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(Long databaseId) {
        this.databaseId = databaseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
