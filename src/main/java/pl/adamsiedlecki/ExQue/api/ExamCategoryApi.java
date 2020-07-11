package pl.adamsiedlecki.ExQue.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.adamsiedlecki.ExQue.db.entity.ExamCategory;
import pl.adamsiedlecki.ExQue.db.service.ExamCategoryService;
import pl.adamsiedlecki.ExQue.util.NumberThings;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/exam-categories")
public class ExamCategoryApi {

    private final ExamCategoryService examCategoryService;

    @Autowired
    public ExamCategoryApi(ExamCategoryService examCategoryService) {
        this.examCategoryService = examCategoryService;
    }

    @GetMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<List<ExamCategory>> getExamCategories(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                @RequestParam(value = "limit", defaultValue = "50") int limit,
                                                                @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
        Pageable pageable;
        if (sort.equals("desc")) {
            pageable = PageRequest.of(page, limit, Sort.by("id").descending());
        } else {
            pageable = PageRequest.of(page, limit, Sort.by("id").ascending());
        }

        return new ResponseEntity<>(examCategoryService.findAll(pageable).getContent(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            })
    public ResponseEntity<ExamCategory> getExamCategory(@PathVariable String id) {
        id = id.trim();
        if (NumberThings.isIntNumber(id)) {
            Long exId = Long.parseLong(id);
            Optional<ExamCategory> examCategory = examCategoryService.findById(exId);
            return examCategory.map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(new ExamCategory(), HttpStatus.NOT_FOUND));

        } else {
            return new ResponseEntity<>(new ExamCategory(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ExamCategory> createExamCategory(@Valid @RequestBody ExamCategory examCategory) {
        return new ResponseEntity<>(examCategoryService.saveAndFlush(examCategory), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ExamCategory> updateExamCategory(@PathVariable String id, @Valid @RequestBody ExamCategory examCategory) {
        if (NumberThings.isIntNumber(id)) {
            Long exId = Long.parseLong(id);
            Optional<ExamCategory> examCategoryOptional = examCategoryService.findById(exId);
            if (examCategoryOptional.isPresent()) {

                examCategoryOptional.get().setName(examCategory.getName());
                examCategoryOptional.get().setDescription(examCategory.getDescription());
                examCategoryOptional.get().setOptionalImagePath(examCategory.getOptionalImagePath());
                examCategoryOptional.get().setThemeColorHex(examCategory.getThemeColorHex());

                return new ResponseEntity<>(examCategoryOptional.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ExamCategory(), HttpStatus.NOT_FOUND);
            }

        } else {
            return new ResponseEntity<>(new ExamCategory(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            })
    public ResponseEntity<Void> deleteExamCategory(@PathVariable String id) {
        if (NumberThings.isIntNumber(id)) {
            Long exId = Long.parseLong(id);
            Optional<ExamCategory> examCategoryOptional = examCategoryService.findById(exId);
            if (examCategoryOptional.isPresent()) {
                examCategoryService.delete(examCategoryOptional.get());
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
