package pl.adamsiedlecki.ExQue.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Answer {

    @Id
    @GeneratedValue
    private long id;
    private String content;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;
        Answer answer = (Answer) o;
        return getId() == answer.getId() &&
                getContent().equals(answer.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getContent());
    }


    public static final class AnswerBuilder {
        private final Answer answer;

        private AnswerBuilder() {
            answer = new Answer();
        }

        public static AnswerBuilder anAnswer() {
            return new AnswerBuilder();
        }

        public AnswerBuilder withId(long id) {
            answer.setId(id);
            return this;
        }

        public AnswerBuilder withContent(String content) {
            answer.setContent(content);
            return this;
        }

        public AnswerBuilder but() {
            return anAnswer().withId(answer.getId()).withContent(answer.getContent());
        }

        public Answer build() {
            return answer;
        }
    }
}
