package via.examsystem.Controller;

import via.examsystem.Service.AnswerService;
import via.examsystem.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@CrossOrigin(
        origins = "http://localhost:3000",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH},
        allowCredentials = "true"
)
@RestController
@RequestMapping("/answers")
public class AnswerController {


    @Autowired
    private AnswerService answerService;

    @GetMapping("/")
    public List<Answer> getAllAnswers() {
        return answerService.findAllAnswers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Answer> getAnswerById(@PathVariable Long id) {
        Answer answer = answerService.getAnswerById(id);
        if (answer != null) {
            return ResponseEntity.ok(answer);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public Answer createAnswer(@RequestBody Answer answers) {
        return answerService.createAnswer(answers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable Long id, @RequestBody Answer answerDetails) {
        Answer updatedAnswer = answerService.updateAnswer(id, answerDetails);
        if (updatedAnswer != null) {
            return ResponseEntity.ok(updatedAnswer);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long id) {
        if (answerService.deleteAnswer(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


}
