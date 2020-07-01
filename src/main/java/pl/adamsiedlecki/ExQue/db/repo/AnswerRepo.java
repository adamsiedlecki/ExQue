package pl.adamsiedlecki.ExQue.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.adamsiedlecki.ExQue.db.entity.Answer;

@Repository
public interface AnswerRepo extends JpaRepository<Answer, Long> {
}
