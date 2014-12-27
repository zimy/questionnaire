package me.zimy.questionnaire.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonIgnore
    @GeneratedValue
    Long id;
    @NotNull
    Likeness response;
    @JsonBackReference
    @ManyToOne
    Question question;
    @JsonBackReference
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

    @JsonProperty
    public Long questionId() {
        return question.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response1 = (Response) o;

        if (question != null ? !question.equals(response1.question) : response1.question != null) return false;
        if (responder != null ? !responder.equals(response1.responder) : response1.responder != null) return false;
        if (response != response1.response) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = response != null ? response.hashCode() : 0;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (responder != null ? responder.hashCode() : 0);
        return result;
    }
}
