package via.examsystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import via.examsystem.Service.QuestionService;
import via.examsystem.model.Question;

import java.util.List;

@CrossOrigin(
        origins = "http://localhost:3000",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH},
        allowCredentials = "true"
)
@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public List<Question> getAllQuestions() {
        return questionService.findAllQuestions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        Question question = questionService.getQuestionById(id);
        if (question != null) {
            return ResponseEntity.ok(question);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public Question createQuestion(@RequestBody Question question) {
        return questionService.createQuestion(question);
    }

    @PostMapping("/batch")
    public ResponseEntity<?> createQuestions(@RequestBody List<Question> questions) {
        questionService.createQuestions(questions);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question questionDetails) {
        Question updatedQuestion = questionService.updateQuestion(id, questionDetails);
        if (updatedQuestion != null) {
            return ResponseEntity.ok(updatedQuestion);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        if (questionService.deleteQuestion(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
