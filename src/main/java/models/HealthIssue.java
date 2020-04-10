package models;

import interfaces.ICsv;

import java.util.List;

public class HealthIssue extends Post implements ICsv {
    private Status status;

    public HealthIssue(Integer id, String title, String description, User user, List<Category> categoryList, Status status) {
        super(id, title, description, user, categoryList);
        this.postType = PostType.HEALTH_ISSUE;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String writeToCsv() {
        return String.format("%d,%s,\"%s\",%d,%s,%s", id, title, description, user.getId(), categoriesToCsvFormat(), status);
    }
}
