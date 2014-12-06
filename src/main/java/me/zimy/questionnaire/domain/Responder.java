package me.zimy.questionnaire.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    int age;
    Domain domain;
    Gender gender;
    String identifier;
    @OneToMany
    List<Response> responses;

    public Responder(int age, Domain domain, Gender gender, String identifier) {

        this.age = age;
        this.domain = domain;
        this.gender = gender;
        this.identifier = identifier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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
