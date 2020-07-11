package pl.adamsiedlecki.ExQue.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.adamsiedlecki.ExQue.db.entity.Answer;
import pl.adamsiedlecki.ExQue.db.service.AnswerService;
import pl.adamsiedlecki.ExQue.util.NumberThings;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/answers")
public class AnswerApi {

    private final AnswerService AnswerService;

    @Autowired
    public AnswerApi(AnswerService AnswerService) {
        this.AnswerService = AnswerService;
    }

    @GetMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<List<Answer>> getExamCategories(@RequestParam(value = "page", defaultValue = "0") int page,
                                                          @RequestParam(value = "limit", defaultValue = "50") int limit,
                                                          @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
        Pageable pageable;
        if (sort.equals("desc")) {
            pageable = PageRequest.of(page, limit, Sort.by("id").descending());
        } else {
            pageable = PageRequest.of(page, limit, Sort.by("id").ascending());
        }

        return new ResponseEntity<>(AnswerService.findAll(pageable).getContent(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            })
    public ResponseEntity<Answer> getAnswer(@PathVariable String id) {
        if (NumberThings.isIntNumber(id)) {
            Long exId = Long.parseLong(id);
            Optional<Answer> Answer = AnswerService.findById(exId);
            return Answer.map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(new Answer(), HttpStatus.NOT_FOUND));

        } else {
            return new ResponseEntity<>(new Answer(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Answer> createAnswer(@Valid @RequestBody Answer Answer) {
        return new ResponseEntity<>(AnswerService.save(Answer), HttpStatus.OK);
    }

    @PutMapping(path = "/id", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Answer> updateAnswer(@PathVariable String id, @Valid @RequestBody Answer Answer) {
        if (NumberThings.isIntNumber(id)) {
            Long exId = Long.parseLong(id);
            Optional<Answer> AnswerOptional = AnswerService.findById(exId);
            if (AnswerOptional.isPresent()) {

                AnswerOptional.get().setContent(Answer.getContent());

                return new ResponseEntity<>(AnswerOptional.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Answer(), HttpStatus.NOT_FOUND);
            }

        } else {
            return new ResponseEntity<>(new Answer(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            })
    public ResponseEntity<Void> deleteAnswer(@PathVariable String id) {
        if (NumberThings.isIntNumber(id)) {
            Long exId = Long.parseLong(id);
            Optional<Answer> AnswerOptional = AnswerService.findById(exId);
            if (AnswerOptional.isPresent()) {
                AnswerService.delete(AnswerOptional.get());
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
