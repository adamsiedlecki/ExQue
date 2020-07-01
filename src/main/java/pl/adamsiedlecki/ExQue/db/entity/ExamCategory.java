package pl.adamsiedlecki.ExQue.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class ExamCategory {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;
    private String optionalImagePath;
    private String themeColorHex;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOptionalImagePath() {
        return optionalImagePath;
    }

    public void setOptionalImagePath(String optionalImagePath) {
        this.optionalImagePath = optionalImagePath;
    }

    public String getThemeColorHex() {
        return themeColorHex;
    }

    public void setThemeColorHex(String themeColorHex) {
        this.themeColorHex = themeColorHex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExamCategory)) return false;
        ExamCategory that = (ExamCategory) o;
        return getId() == that.getId() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getOptionalImagePath(), that.getOptionalImagePath()) &&
                Objects.equals(getThemeColorHex(), that.getThemeColorHex());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getOptionalImagePath(), getThemeColorHex());
    }
}
