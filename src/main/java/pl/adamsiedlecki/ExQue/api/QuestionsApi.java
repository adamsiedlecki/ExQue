package pl.adamsiedlecki.ExQue.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.adamsiedlecki.ExQue.db.entity.Question;
import pl.adamsiedlecki.ExQue.db.service.QuestionService;
import pl.adamsiedlecki.ExQue.util.NumberThings;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionsApi {

    private final QuestionService questionService;

    @Autowired
    public QuestionsApi(QuestionService QuestionService) {
        this.questionService = QuestionService;
    }

    @GetMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<List<Question>> getExamCategories(@RequestParam(value = "page", defaultValue = "0") int page,
                                                            @RequestParam(value = "limit", defaultValue = "50") int limit,
                                                            @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
        Pageable pageable;
        if (sort.equals("desc")) {
            pageable = PageRequest.of(page, limit, Sort.by("id").descending());
        } else {
            pageable = PageRequest.of(page, limit, Sort.by("id").ascending());
        }

        return new ResponseEntity<>(questionService.findAll(pageable).getContent(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            })
    public ResponseEntity<Question> getQuestion(@PathVariable String id) {
        id = id.trim();
        if (NumberThings.isIntNumber(id)) {
            Long exId = Long.parseLong(id);
            Optional<Question> Question = questionService.findById(exId);
            return Question.map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(new Question(), HttpStatus.NOT_FOUND));

        } else {
            return new ResponseEntity<>(new Question(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Question> createQuestion(@Valid @RequestBody Question question) {
        return new ResponseEntity<>(questionService.saveAndFlush(question), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Question> updateQuestion(@PathVariable String id, @Valid @RequestBody Question Question) {
        if (NumberThings.isIntNumber(id)) {
            Long exId = Long.parseLong(id);
            Optional<Question> QuestionOptional = questionService.findById(exId);
            if (QuestionOptional.isPresent()) {

                QuestionOptional.get().setContent(Question.getContent());
                QuestionOptional.get().setCorrectAnswer(Question.getCorrectAnswer());
                QuestionOptional.get().setOptionalImagePath(Question.getOptionalImagePath());
                QuestionOptional.get().setExamCategory(Question.getExamCategory());
                QuestionOptional.get().setPossibleAnswers(Question.getPossibleAnswers());

                return new ResponseEntity<>(QuestionOptional.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Question(), HttpStatus.NOT_FOUND);
            }

        } else {
            return new ResponseEntity<>(new Question(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            })
    public ResponseEntity<Void> deleteQuestion(@PathVariable String id) {
        if (NumberThings.isIntNumber(id)) {
            Long exId = Long.parseLong(id);
            Optional<Question> QuestionOptional = questionService.findById(exId);
            if (QuestionOptional.isPresent()) {
                questionService.delete(QuestionOptional.get());
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
