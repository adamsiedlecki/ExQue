package pl.adamsiedlecki.ExQue.buildInData;

import pl.adamsiedlecki.ExQue.db.entity.Answer;
import pl.adamsiedlecki.ExQue.db.entity.ExamCategory;
import pl.adamsiedlecki.ExQue.db.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionList extends ArrayList<Question> {

    public QuestionList() {
        ExamCategoriesList categories = new ExamCategoriesList();
        AnswersList answers = new AnswersList();

        Question ques1 = Question.QuestionBuilder
                .aQuestion()
                .withExamCategory(categories.get(0))
                .withContent("What is a correct syntax to output \"Hello World\" in Java?")
                .withCorrectAnswer(answers.get(0))
                .withPossibleAnswers(List.of(answers.get(0), answers.get(1), answers.get(2), answers.get(3)))
                .build();

        Question ques2 = Question.QuestionBuilder
                .aQuestion()
                .withExamCategory(categories.get(1))
                .withContent("What is a correct syntax to output \"Hello World\" in Python?")
                .withCorrectAnswer(answers.get(5))
                .withPossibleAnswers(List.of(answers.get(4), answers.get(5), answers.get(6), answers.get(7)))
                .build();

        addAll(List.of(ques1, ques2));
    }

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
                    .withThemeColorHex("#4287f5")
                    .build();

            addAll(List.of(cat1, cat2));
        }
    }

    public class AnswersList extends ArrayList<Answer> {

        public AnswersList() {
            Answer ans0 = Answer.AnswerBuilder
                    .anAnswer()
                    .withContent("System.out.println(\"Hello World\")")
                    .build();

            Answer ans1 = Answer.AnswerBuilder
                    .anAnswer()
                    .withContent("print Hello World")
                    .build();

            Answer ans2 = Answer.AnswerBuilder
                    .anAnswer()
                    .withContent("echo Hello World")
                    .build();

            Answer ans3 = Answer.AnswerBuilder
                    .anAnswer()
                    .withContent("Console.write(Hello World)")
                    .build();

            Answer ans4 = Answer.AnswerBuilder
                    .anAnswer()
                    .withContent("print(\"Hello World\")")
                    .build();

            Answer ans5 = Answer.AnswerBuilder
                    .anAnswer()
                    .withContent("Console.print(\"Hello World\")")
                    .build();

            Answer ans6 = Answer.AnswerBuilder
                    .anAnswer()
                    .withContent("System.print(\"Hello World\")")
                    .build();

            Answer ans7 = Answer.AnswerBuilder
                    .anAnswer()
                    .withContent("Printer.print(\"Hello World\")")
                    .build();

            addAll(List.of(ans0, ans1, ans2, ans3, ans4, ans5, ans6, ans7));
        }
    }
}
