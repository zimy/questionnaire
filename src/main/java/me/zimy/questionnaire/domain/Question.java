package me.zimy.questionnaire.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This is for two usages: getting question list from database and storing results.
 * It contains question itself and target gender.
 *
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/1/14.
 */
@Entity
public class Question {

    @Id
    @GeneratedValue
    Long id;
    String question;
    Gender targetGender;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)
    List<Response> responseList = new ArrayList<>();

    public Question() {
        question = "";
    }

    public Question(String question) {
        this.question = question;
    }

    public List<Response> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<Response> responseList) {
        this.responseList = responseList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Gender getTargetGender() {
        return targetGender;
    }

    public void setTargetGender(Gender targetGender) {
        this.targetGender = targetGender;
    }
}
