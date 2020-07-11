package pl.adamsiedlecki.ExQue.buildInData;

import pl.adamsiedlecki.ExQue.db.entity.ExamCategory;

import java.util.ArrayList;
import java.util.List;

public class ExamCategoriesList extends ArrayList<ExamCategory> {

    public ExamCategoriesList() {
        ExamCategory cat1 = ExamCategory.ExamCategoryBuilder
                .anExamCategory()
                .withName("Java")
                .withDescription("java quiz")
                .withThemeColorHex("#eb6734")
                .build();

        ExamCategory cat2 = ExamCategory.ExamCategoryBuilder
                .anExamCategory()
                .withName("Python")
                .withDescription("python quiz")
                .withThemeColorHex("#eb6734")
                .build();

        addAll(List.of(cat1, cat2));
    }
}
