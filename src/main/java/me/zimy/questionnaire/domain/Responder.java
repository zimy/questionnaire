package me.zimy.questionnaire.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * This represents main persons of questionnaire -- responders.
 * It contains name, age, gender and reason to be asked for questionnaire filling.
 *
 * @author Dmitriy &lt;Zimy&gt; Yakovlev
 * @since 12/4/14.
 */
@Entity
public class Responder {
    @Id
    @GeneratedValue
    Long id;
    @NotNull
    @Length(min = 1)
    String age;
    @NotNull
    Domain domain;
    @NotNull
    Gender gender;
    @NotNull
    @Length(min = 1)
    String identifier;
    @OneToMany
    List<Response> responses;

    public Responder() {
        age = "174 ДЯБ";
        domain = Domain.Nothing;
        gender = null;
        identifier = "Неопознанный хорёк";
        responses = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

}
