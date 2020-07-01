package pl.adamsiedlecki.ExQue.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.adamsiedlecki.ExQue.db.entity.ExamCategory;

@Repository
public interface ExamCategoryRepo extends JpaRepository<ExamCategory, Long> {
}
