package me.zimy.questionnaire.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * This is for two usages: getting question list from database and storing results.
 * It contains question itself and target gender.
 *
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/1/14.
 */
public class Question {

    @Id
    String id;
    String question;
    Gender targetGender;
    Integer Criteria;
    @JsonManagedReference
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public int getCriteria() {
        return Criteria;
    }

    public void setCriteria(int criteria) {
        Criteria = criteria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question1 = (Question) o;

        if (Criteria != null ? !Criteria.equals(question1.Criteria) : question1.Criteria != null) return false;
        if (question != null ? !question.equals(question1.question) : question1.question != null) return false;
        return targetGender == question1.targetGender;

    }

    @Override
    public int hashCode() {
        int result = question != null ? question.hashCode() : 0;
        result = 31 * result + (targetGender != null ? targetGender.hashCode() : 0);
        result = 31 * result + (Criteria != null ? Criteria.hashCode() : 0);
        return result;
    }
}
