package pl.adamsiedlecki.ExQue.db.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Question {

    @Id
    @GeneratedValue
    private long id;
    private String content = "";
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "correct_answer_id", referencedColumnName = "id")
    private Answer correctAnswer;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Answer> possibleAnswers;
    private String optionalImagePath;
    @OneToOne(cascade = CascadeType.ALL)
    private ExamCategory examCategory;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return getId() == question.getId() &&
                Objects.equals(getContent(), question.getContent()) &&
                Objects.equals(getCorrectAnswer(), question.getCorrectAnswer()) &&
                Objects.equals(getPossibleAnswers(), question.getPossibleAnswers()) &&
                Objects.equals(getOptionalImagePath(), question.getOptionalImagePath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getContent(), getCorrectAnswer(), getPossibleAnswers(), getOptionalImagePath());
    }

    public ExamCategory getExamCategory() {
        return examCategory;
    }

    public void setExamCategory(ExamCategory examCategory) {
        this.examCategory = examCategory;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Answer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Answer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<Answer> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<Answer> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public String getOptionalImagePath() {
        return optionalImagePath;
    }

    public void setOptionalImagePath(String optionalImagePath) {
        this.optionalImagePath = optionalImagePath;
    }


    public static final class QuestionBuilder {
        private final Question question;

        private QuestionBuilder() {
            question = new Question();
        }

        public static QuestionBuilder aQuestion() {
            return new QuestionBuilder();
        }

        public QuestionBuilder withId(long id) {
            question.setId(id);
            return this;
        }

        public QuestionBuilder withContent(String content) {
            question.setContent(content);
            return this;
        }

        public QuestionBuilder withCorrectAnswer(Answer correctAnswer) {
            question.setCorrectAnswer(correctAnswer);
            return this;
        }

        public QuestionBuilder withPossibleAnswers(List<Answer> possibleAnswers) {
            question.setPossibleAnswers(possibleAnswers);
            return this;
        }

        public QuestionBuilder withOptionalImagePath(String optionalImagePath) {
            question.setOptionalImagePath(optionalImagePath);
            return this;
        }

        public QuestionBuilder withExamCategory(ExamCategory examCategory) {
            question.setExamCategory(examCategory);
            return this;
        }

        public QuestionBuilder but() {
            return aQuestion().withId(question.getId()).withContent(question.getContent()).withCorrectAnswer(question.getCorrectAnswer()).withPossibleAnswers(question.getPossibleAnswers()).withOptionalImagePath(question.getOptionalImagePath()).withExamCategory(question.getExamCategory());
        }

        public Question build() {
            return question;
        }
    }
}
