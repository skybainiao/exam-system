package via.examsystem.Service;

import via.examsystem.Repository.QuestionRepository;
import via.examsystem.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {


    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> findAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question updateQuestion(Long id, Question questionDetails) {
        Question question = questionRepository.findById(id).orElse(null);
        if (question != null) {
            question.setContent(questionDetails.getContent());
            question.setExam(questionDetails.getExam());
            question.setCorrectAnswer(questionDetails.getCorrectAnswer());
            questionRepository.save(question);
            return question;
        }
        return null;
    }

    public boolean deleteQuestion(Long id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            questionRepository.delete(question.get());
            return true;
        }
        return false;
    }

}
