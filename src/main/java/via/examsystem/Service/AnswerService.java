package via.examsystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import via.examsystem.Repository.AnswerRepository;
import via.examsystem.model.Answer;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public List<Answer> findAllAnswers() {
        return answerRepository.findAll();
    }

    public Answer getAnswerById(Long id) {
        return answerRepository.findById(id).orElse(null);
    }

    public List<Answer> createAnswers(List<Answer> answers) {
        return answerRepository.saveAll(answers);
    }

    public Answer updateAnswer(Long id, Answer answerDetails) {
        Optional<Answer> answerOpt = answerRepository.findById(id);
        if (answerOpt.isPresent()) {
            Answer answer = answerOpt.get();
            answer.setStudentAnswer(answerDetails.getStudentAnswer());
            return answerRepository.save(answer);
        }
        return null;
    }

    public boolean deleteAnswer(Long id) {
        if (answerRepository.existsById(id)) {
            answerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
