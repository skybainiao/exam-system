package via.examsystem.Service;

import via.examsystem.Repository.ExamRepository;
import via.examsystem.model.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    public List<Exam> findAllExams() {
        return examRepository.findAll();
    }

    public Exam getExamById(Long id) {
        return examRepository.findById(id).orElse(null);
    }

    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    public Exam updateExam(Long id, Exam examDetails) {
        Exam exam = examRepository.findById(id).orElse(null);
        if (exam != null) {
            exam.setTitle(examDetails.getTitle());
            exam.setExamDate(examDetails.getExamDate());
            exam.setExamPassword(examDetails.getExamPassword());
            exam.setCourse(examDetails.getCourse());
            return examRepository.save(exam);
        }
        return null;
    }

    public boolean deleteExam(Long id) {
        if (examRepository.existsById(id)) {
            examRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean setExamPassword(Long examId, String password) {
        Exam exam = examRepository.findById(examId).orElse(null);
        if (exam != null) {
            exam.setExamPassword(password);
            examRepository.save(exam);
            return true;
        }
        return false;
    }

    public Exam validateExamPassword(String examPassword, String password) {
        return examRepository.findByExamPasswordAndExamPassword(examPassword, password);
    }
}
