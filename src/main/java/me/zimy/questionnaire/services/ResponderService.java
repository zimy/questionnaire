package me.zimy.questionnaire.services;

import me.zimy.questionnaire.domain.Responder;

import java.util.List;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/7/14.
 */
public interface ResponderService {
    Responder save(Responder responder);

    Responder findOne(Long id);

    List<Responder> getAll();
}
