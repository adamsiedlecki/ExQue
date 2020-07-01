package pl.adamsiedlecki.ExQue.db.entity;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import java.util.Objects;

@Entity
public class Question {

    @Id
    @GeneratedValue
    private long id;
    private String content = "";
    private Answer correctAnswer;
    private List<Answer> possibleAnswers;
    private String optionalImagePath;

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
}
