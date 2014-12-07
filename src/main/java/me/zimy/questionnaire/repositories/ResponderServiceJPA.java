package me.zimy.questionnaire.repositories;

import me.zimy.questionnaire.domain.Responder;
import me.zimy.questionnaire.services.ResponderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/7/14.
 */
@Service
public class ResponderServiceJPA implements ResponderService {

    @Autowired
    private ResponderRepository repository;

    @Override
    public Responder save(Responder responder) {
        return repository.save(responder);
    }

    @Override
    public Responder findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Responder> getAll() {
        return repository.findAll();
    }
}
