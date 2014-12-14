package me.zimy.questionnaire.services;

import me.zimy.questionnaire.domain.Gender;
import me.zimy.questionnaire.domain.Question;

import java.util.List;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/7/14.
 */
public interface QuestionService {
    Question save(Question question);

    Question find(Long id);

    List<Question> getAll();

    List<Question> getByGender(Gender gender);

    Question find(String text);
}
