package via.examsystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import via.examsystem.Service.ExamService;
import via.examsystem.model.Exam;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(
        origins = "http://localhost:3000",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH},
        allowCredentials = "true"
)
@RestController
@RequestMapping("/exams")
public class ExamController {

    private static final Logger logger = LoggerFactory.getLogger(ExamController.class);

    @Autowired
    private ExamService examService;

    @GetMapping("/")
    public List<Exam> getAllExams() {
        List<Exam> exams = examService.findAllExams();
        logger.info("Fetched exams: " + exams);
        return exams;
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
            return ResponseEntity.badRequest().body("{\"message\": \"Failed to set password\"}");
        }
    }

    @PostMapping("/validate-password/{examPassword}")
    public ResponseEntity<?> validatePassword(@PathVariable String examPassword, @RequestBody Map<String, String> request) {
        String password = request.get("password");
        Exam exam = examService.validateExamPassword(examPassword, password);
        if (exam != null) {
            return ResponseEntity.ok(exam);
        } else {
            return ResponseEntity.badRequest().body("{\"message\": \"Invalid password\"}");
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
