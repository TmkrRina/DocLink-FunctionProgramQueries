package models;

import interfaces.ICategoriesToCsv;
import interfaces.ICsv;

import javax.print.Doc;
import java.util.List;

public class Announcement extends Post implements ICsv {
    public Announcement(Integer id, String title, String description, Doctor doctor, List<Category> categoryList) {
        super(id, title, description, doctor.getUser(), categoryList);
        this.postType = PostType.ANNOUNCEMENT;
    }

    @Override
    public String writeToCsv() {
        return String.format("%d,%s,\"%s\",%d,%s", id, title, description, user.getId(), categoriesToCsvFormat());
    }
}
