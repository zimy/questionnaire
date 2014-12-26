package me.zimy.questionnaire.repositories;

import me.zimy.questionnaire.domain.Response;
import me.zimy.questionnaire.services.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/10/14.
 */
@Repository
@Transactional
public class ResponseServiceJPA implements ResponseService {

    @Autowired
    private ResponseRepository responseRepository;

    @Override
    public Response save(Response response) {
        return responseRepository.save(response);
    }

    @Override
    public List<Response> getAll() {
        return responseRepository.findAll();
    }
}
