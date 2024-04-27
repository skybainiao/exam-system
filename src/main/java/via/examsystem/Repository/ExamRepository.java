package via.examsystem.Repository;


import via.examsystem.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {


}