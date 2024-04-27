package via.examsystem.Service;

import via.examsystem.Repository.AnswerRepository;

import via.examsystem.model.Answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Long id, Answer answerDetails) {
        Answer answer = answerRepository.findById(id).orElse(null);
        if (answer != null) {
            answer.setQuestion(answerDetails.getQuestion());
            answer.setStudent(answerDetails.getStudent());
            answer.setStudentAnswer(answerDetails.getStudentAnswer());
            answerRepository.save(answer);
            return answer;
        }
        return null;
    }

    public boolean deleteAnswer(Long id) {
        Optional<Answer> answer = answerRepository.findById(id);
        if (answer.isPresent()) {
            answerRepository.delete(answer.get());
            return true;
        }
        return false;
    }
}
