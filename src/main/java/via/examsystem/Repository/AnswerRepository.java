package via.examsystem.Repository;

import via.examsystem.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {



}
