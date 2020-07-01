package pl.adamsiedlecki.ExQue.db.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.adamsiedlecki.ExQue.db.entity.ExamCategory;
import pl.adamsiedlecki.ExQue.db.repo.ExamCategoryRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ExamCategoryService {

    private ExamCategoryRepo examCategoryRepo;

    public ExamCategoryService(ExamCategoryRepo examCategoryRepo) {
        this.examCategoryRepo = examCategoryRepo;
    }

    public List<ExamCategory> findAll() {
        return examCategoryRepo.findAll();
    }

    public List<ExamCategory> findAll(Sort sort) {
        return examCategoryRepo.findAll(sort);
    }

    public List<ExamCategory> findAllById(Iterable<Long> iterable) {
        return examCategoryRepo.findAllById(iterable);
    }

    public <S extends ExamCategory> List<S> saveAll(Iterable<S> iterable) {
        return examCategoryRepo.saveAll(iterable);
    }

    public void flush() {
        examCategoryRepo.flush();
    }

    public <S extends ExamCategory> S saveAndFlush(S s) {
        return examCategoryRepo.saveAndFlush(s);
    }

    public void deleteInBatch(Iterable<ExamCategory> iterable) {
        examCategoryRepo.deleteInBatch(iterable);
    }

    public void deleteAllInBatch() {
        examCategoryRepo.deleteAllInBatch();
    }

    public ExamCategory getOne(Long aLong) {
        return examCategoryRepo.getOne(aLong);
    }

    public <S extends ExamCategory> List<S> findAll(Example<S> example) {
        return examCategoryRepo.findAll(example);
    }

    public <S extends ExamCategory> List<S> findAll(Example<S> example, Sort sort) {
        return examCategoryRepo.findAll(example, sort);
    }

    public Page<ExamCategory> findAll(Pageable pageable) {
        return examCategoryRepo.findAll(pageable);
    }

    public <S extends ExamCategory> S save(S s) {
        return examCategoryRepo.save(s);
    }

    public Optional<ExamCategory> findById(Long aLong) {
        return examCategoryRepo.findById(aLong);
    }

    public boolean existsById(Long aLong) {
        return examCategoryRepo.existsById(aLong);
    }

    public long count() {
        return examCategoryRepo.count();
    }

    public void deleteById(Long aLong) {
        examCategoryRepo.deleteById(aLong);
    }

    public void delete(ExamCategory examCategory) {
        examCategoryRepo.delete(examCategory);
    }

    public void deleteAll(Iterable<? extends ExamCategory> iterable) {
        examCategoryRepo.deleteAll(iterable);
    }

    public void deleteAll() {
        examCategoryRepo.deleteAll();
    }

    public <S extends ExamCategory> Optional<S> findOne(Example<S> example) {
        return examCategoryRepo.findOne(example);
    }

    public <S extends ExamCategory> Page<S> findAll(Example<S> example, Pageable pageable) {
        return examCategoryRepo.findAll(example, pageable);
    }

    public <S extends ExamCategory> long count(Example<S> example) {
        return examCategoryRepo.count(example);
    }

    public <S extends ExamCategory> boolean exists(Example<S> example) {
        return examCategoryRepo.exists(example);
    }
}
