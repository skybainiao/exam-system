package via.examsystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import via.examsystem.Service.ExamService;
import via.examsystem.model.Exam;

import java.util.List;
import java.util.Optional;

@CrossOrigin(
        origins = "http://localhost:3000",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH},
        allowCredentials = "true"
)
@RestController
@RequestMapping("/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @GetMapping("/")
    public List<Exam> getAllExams() {
        return examService.findAllExams();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExamById(@PathVariable Long id) {
        Exam exam = examService.getExamById(id);
        if (exam != null) {
            return ResponseEntity.ok(exam);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/set-password/{examId}")
    public ResponseEntity<?> setPassword(@PathVariable Long examId, @RequestBody String password) {
        boolean isSet = examService.setExamPassword(examId, password);
        if (isSet) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Failed to set password");
        }
    }

    @PostMapping("/validate-password")
    public ResponseEntity<?> validatePassword(@RequestBody PasswordRequest passwordRequest) {
        Optional<Exam> examOpt = examService.validateExamPassword(passwordRequest.getPassword());
        if (examOpt.isPresent()) {
            return ResponseEntity.ok(examOpt.get());
        } else {
            return ResponseEntity.badRequest().body("Invalid password");
        }
    }

    @PostMapping("/")
    public Exam createExam(@RequestBody Exam exam) {
        return examService.createExam(exam);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable Long id, @RequestBody Exam examDetails) {
        Exam updatedExam = examService.updateExam(id, examDetails);
        if (updatedExam != null) {
            return ResponseEntity.ok(updatedExam);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        if (examService.deleteExam(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

class PasswordRequest {
    private String password;

    // getters and setters

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
