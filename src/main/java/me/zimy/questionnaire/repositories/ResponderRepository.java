package me.zimy.questionnaire.repositories;

import me.zimy.questionnaire.domain.Responder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is for usage with Spring Data JPA project
 *
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/4/14.
 */
@Repository
public interface ResponderRepository extends JpaRepository<Responder, Long> {
}
