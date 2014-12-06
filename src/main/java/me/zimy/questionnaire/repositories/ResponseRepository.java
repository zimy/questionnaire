package me.zimy.questionnaire.repositories;

import me.zimy.questionnaire.domain.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is for usage with Spring Data JPA project
 *
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/1/14.
 */
@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {
}
