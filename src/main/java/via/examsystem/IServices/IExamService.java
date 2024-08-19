package via.examsystem.IServices;

import via.examsystem.model.Exam;
import java.util.List;

public interface IExamService {

    List<Exam> findAllExams();

    Exam getExamById(Long id);

    Exam createExam(Exam exam);

    Exam updateExam(Long id, Exam examDetails);

    boolean deleteExam(Long id);

    boolean setExamPassword(Long examId, String password);

    Exam validateExamPassword(String examPassword, String password);
}
