package me.zimy.questionnaire.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * This is for storing concrete answer for linked question and selecting with responder
 *
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/1/14.
 */
@Entity
public class Response {
    @Id
    @GeneratedValue
    Long id;
    @NotNull
    Likeness response;
    @ManyToOne
    Question question;
    @ManyToOne
    Responder responder;

    public Response() {
        response = null;
    }

    public Response(Likeness response, Question question) {
        this.response = response;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Likeness getResponse() {
        return response;
    }

    public void setResponse(Likeness response) {
        this.response = response;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Responder getResponder() {
        return responder;
    }

    public void setResponder(Responder responder) {
        this.responder = responder;
    }
}
