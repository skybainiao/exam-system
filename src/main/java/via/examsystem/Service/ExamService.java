package via.examsystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import via.examsystem.Repository.ExamRepository;
import via.examsystem.model.Exam;

import java.util.List;
import java.util.Optional;

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
        Optional<Exam> examOpt = examRepository.findById(id);
        if (examOpt.isPresent()) {
            Exam exam = examOpt.get();
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
        Optional<Exam> examOpt = examRepository.findById(examId);
        if (examOpt.isPresent()) {
            Exam exam = examOpt.get();
            exam.setExamPassword(password);
            examRepository.save(exam);
            return true;
        }
        return false;
    }

    public Optional<Exam> validateExamPassword(String password) {
        return examRepository.findByExamPassword(password);
    }
}
