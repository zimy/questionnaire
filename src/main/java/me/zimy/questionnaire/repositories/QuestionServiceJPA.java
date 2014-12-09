package me.zimy.questionnaire.repositories;

import me.zimy.questionnaire.domain.Gender;
import me.zimy.questionnaire.domain.Question;
import me.zimy.questionnaire.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/7/14.
 */
@Service
public class QuestionServiceJPA implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question find(Long id) {
        return questionRepository.findOne(id);
    }

    @Override
    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    @Override
    public List<Question> getByGender(Gender gender) {
        return questionRepository.getByTargetGender(gender);
    }
}
