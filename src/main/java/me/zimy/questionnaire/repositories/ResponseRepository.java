package me.zimy.questionnaire.repositories;

import me.zimy.questionnaire.domain.Response;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This is for usage with Spring Data JPA project
 *
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/1/14.
 */
@Repository
public interface ResponseRepository extends MongoRepository<Response, Long> {
}
