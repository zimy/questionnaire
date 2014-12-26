package me.zimy.questionnaire.services;

import me.zimy.questionnaire.domain.Response;

import java.util.List;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/10/14.
 */
public interface ResponseService {
    Response save(Response response);

    List<Response> getAll();
}
