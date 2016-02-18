package me.zimy.questionnaire.repositories;

import me.zimy.questionnaire.domain.Gender;
import me.zimy.questionnaire.domain.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is for usage with Spring Data JPA project
 *
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/1/14.
 */
@Repository
public interface QuestionRepository extends MongoRepository<Question, Long> {
    List<Question> getByTargetGender(Gender gender);

    Question getByQuestion(String question);
}