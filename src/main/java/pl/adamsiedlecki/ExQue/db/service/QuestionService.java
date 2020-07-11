package pl.adamsiedlecki.ExQue.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.adamsiedlecki.ExQue.buildInData.QuestionList;
import pl.adamsiedlecki.ExQue.db.entity.Question;
import pl.adamsiedlecki.ExQue.db.repo.QuestionRepo;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepo questionRepo;

    @Autowired
    public QuestionService(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
        if (questionRepo.count() == 0) {
            saveAll(new QuestionList());
            flush();
        }
    }

    public List<Question> findAll() {
        return questionRepo.findAll();
    }

    public List<Question> findAll(Sort sort) {
        return questionRepo.findAll(sort);
    }

    public List<Question> findAllById(Iterable<Long> iterable) {
        return questionRepo.findAllById(iterable);
    }

    public <S extends Question> List<S> saveAll(Iterable<S> iterable) {
        return questionRepo.saveAll(iterable);
    }

    public void flush() {
        questionRepo.flush();
    }

    public <S extends Question> S saveAndFlush(S s) {
        return questionRepo.saveAndFlush(s);
    }

    public void deleteInBatch(Iterable<Question> iterable) {
        questionRepo.deleteInBatch(iterable);
    }

    public void deleteAllInBatch() {
        questionRepo.deleteAllInBatch();
    }

    public Question getOne(Long aLong) {
        return questionRepo.getOne(aLong);
    }

    public <S extends Question> List<S> findAll(Example<S> example) {
        return questionRepo.findAll(example);
    }

    public <S extends Question> List<S> findAll(Example<S> example, Sort sort) {
        return questionRepo.findAll(example, sort);
    }

    public Page<Question> findAll(Pageable pageable) {
        return questionRepo.findAll(pageable);
    }

    public <S extends Question> S save(S s) {
        return questionRepo.save(s);
    }

    public Optional<Question> findById(Long aLong) {
        return questionRepo.findById(aLong);
    }

    public boolean existsById(Long aLong) {
        return questionRepo.existsById(aLong);
    }

    public long count() {
        return questionRepo.count();
    }

    public void deleteById(Long aLong) {
        questionRepo.deleteById(aLong);
    }

    public void delete(Question question) {
        questionRepo.delete(question);
    }

    public void deleteAll(Iterable<? extends Question> iterable) {
        questionRepo.deleteAll(iterable);
    }

    public void deleteAll() {
        questionRepo.deleteAll();
    }

    public <S extends Question> Optional<S> findOne(Example<S> example) {
        return questionRepo.findOne(example);
    }

    public <S extends Question> Page<S> findAll(Example<S> example, Pageable pageable) {
        return questionRepo.findAll(example, pageable);
    }

    public <S extends Question> long count(Example<S> example) {
        return questionRepo.count(example);
    }

    public <S extends Question> boolean exists(Example<S> example) {
        return questionRepo.exists(example);
    }
}
