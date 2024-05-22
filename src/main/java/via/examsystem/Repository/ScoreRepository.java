package via.examsystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import via.examsystem.model.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
}
