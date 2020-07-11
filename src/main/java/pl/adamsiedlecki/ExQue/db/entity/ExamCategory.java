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


    public static final class ExamCategoryBuilder {
        private final ExamCategory examCategory;

        private ExamCategoryBuilder() {
            examCategory = new ExamCategory();
        }

        public static ExamCategoryBuilder anExamCategory() {
            return new ExamCategoryBuilder();
        }

        public ExamCategoryBuilder withId(long id) {
            examCategory.setId(id);
            return this;
        }

        public ExamCategoryBuilder withName(String name) {
            examCategory.setName(name);
            return this;
        }

        public ExamCategoryBuilder withDescription(String description) {
            examCategory.setDescription(description);
            return this;
        }

        public ExamCategoryBuilder withOptionalImagePath(String optionalImagePath) {
            examCategory.setOptionalImagePath(optionalImagePath);
            return this;
        }

        public ExamCategoryBuilder withThemeColorHex(String themeColorHex) {
            examCategory.setThemeColorHex(themeColorHex);
            return this;
        }

        public ExamCategoryBuilder but() {
            return anExamCategory().withId(examCategory.getId()).withName(examCategory.getName()).withDescription(examCategory.getDescription()).withOptionalImagePath(examCategory.getOptionalImagePath()).withThemeColorHex(examCategory.getThemeColorHex());
        }

        public ExamCategory build() {
            return examCategory;
        }
    }
}
