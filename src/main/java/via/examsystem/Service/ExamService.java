package via.examsystem.Service;

import via.examsystem.Repository.ExamRepository;

import via.examsystem.model.Exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        Exam exam = examRepository.findById(id).orElse(null);
        if (exam != null) {
            exam.setTitle(examDetails.getTitle());
            exam.setExamDate(examDetails.getExamDate());
            exam.setCourse(examDetails.getCourse());
            examRepository.save(exam);
            return exam;
        }
        return null;
    }

    public boolean deleteExam(Long id) {
        Optional<Exam> exam = examRepository.findById(id);
        if (exam.isPresent()) {
            examRepository.delete(exam.get());
            return true;
        }
        return false;
    }
}
