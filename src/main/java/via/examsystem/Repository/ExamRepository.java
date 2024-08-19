package via.examsystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import via.examsystem.model.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    Exam findByExamPasswordAndExamPassword(String examPassword, String password);

    Exam findByTitle(String title);
}
