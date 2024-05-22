package via.examsystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import via.examsystem.Repository.QuestionRepository;
import via.examsystem.model.Question;

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

    public void createQuestions(List<Question> questions) {
        questionRepository.saveAll(questions);
    }

    public Question updateQuestion(Long id, Question questionDetails) {
        Optional<Question> questionOptional = questionRepository.findById(id);
        if (questionOptional.isPresent()) {
            Question question = questionOptional.get();
            question.setContent(questionDetails.getContent());
            question.setCorrectAnswer(questionDetails.getCorrectAnswer());
            question.setWeight(questionDetails.getWeight());
            question.setExam(questionDetails.getExam());
            return questionRepository.save(question);
        }
        return null;
    }

    public boolean deleteQuestion(Long id) {
        Optional<Question> questionOptional = questionRepository.findById(id);
        if (questionOptional.isPresent()) {
            questionRepository.delete(questionOptional.get());
            return true;
        }
        return false;
    }
}
