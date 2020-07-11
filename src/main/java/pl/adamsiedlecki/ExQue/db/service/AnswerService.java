package pl.adamsiedlecki.ExQue.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.adamsiedlecki.ExQue.db.entity.Answer;
import pl.adamsiedlecki.ExQue.db.repo.AnswerRepo;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    private final AnswerRepo answerRepo;

    @Autowired
    public AnswerService(AnswerRepo answerRepo) {
        this.answerRepo = answerRepo;
        if (count() == 0) {

        }
    }

    public List<Answer> findAll() {
        return answerRepo.findAll();
    }

    public List<Answer> findAll(Sort sort) {
        return answerRepo.findAll(sort);
    }

    public List<Answer> findAllById(Iterable<Long> iterable) {
        return answerRepo.findAllById(iterable);
    }

    public <S extends Answer> List<S> saveAll(Iterable<S> iterable) {
        return answerRepo.saveAll(iterable);
    }

    public void flush() {
        answerRepo.flush();
    }

    public <S extends Answer> S saveAndFlush(S s) {
        return answerRepo.saveAndFlush(s);
    }

    public void deleteInBatch(Iterable<Answer> iterable) {
        answerRepo.deleteInBatch(iterable);
    }

    public void deleteAllInBatch() {
        answerRepo.deleteAllInBatch();
    }

    public Answer getOne(Long aLong) {
        return answerRepo.getOne(aLong);
    }

    public <S extends Answer> List<S> findAll(Example<S> example) {
        return answerRepo.findAll(example);
    }

    public <S extends Answer> List<S> findAll(Example<S> example, Sort sort) {
        return answerRepo.findAll(example, sort);
    }

    public Page<Answer> findAll(Pageable pageable) {
        return answerRepo.findAll(pageable);
    }

    public <S extends Answer> S save(S s) {
        return answerRepo.save(s);
    }

    public Optional<Answer> findById(Long aLong) {
        return answerRepo.findById(aLong);
    }

    public boolean existsById(Long aLong) {
        return answerRepo.existsById(aLong);
    }

    public long count() {
        return answerRepo.count();
    }

    public void deleteById(Long aLong) {
        answerRepo.deleteById(aLong);
    }

    public void delete(Answer answer) {
        answerRepo.delete(answer);
    }

    public void deleteAll(Iterable<? extends Answer> iterable) {
        answerRepo.deleteAll(iterable);
    }

    public void deleteAll() {
        answerRepo.deleteAll();
    }

    public <S extends Answer> Optional<S> findOne(Example<S> example) {
        return answerRepo.findOne(example);
    }

    public <S extends Answer> Page<S> findAll(Example<S> example, Pageable pageable) {
        return answerRepo.findAll(example, pageable);
    }

    public <S extends Answer> long count(Example<S> example) {
        return answerRepo.count(example);
    }

    public <S extends Answer> boolean exists(Example<S> example) {
        return answerRepo.exists(example);
    }
}
