package me.zimy.questionnaire.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

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
public class Responder {
    @Id
    Long id;
    @NotNull
    Long age;
    @NotNull
    Domain domain;
    @NotNull
    Gender gender;
    @NotNull
    @Length(min = 1)
    String identifier;
    @JsonManagedReference
    List<Response> responses;

    public Responder() {
        age = 174L;
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

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
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

    public List<Response> getResponses() {
        return responses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Responder responder = (Responder) o;

        if (age != null ? !age.equals(responder.age) : responder.age != null) return false;
        if (domain != responder.domain) return false;
        if (gender != responder.gender) return false;
        return identifier != null ? identifier.equals(responder.identifier) : responder.identifier == null;

    }

    @Override
    public int hashCode() {
        int result = age != null ? age.hashCode() : 0;
        result = 31 * result + (domain != null ? domain.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (identifier != null ? identifier.hashCode() : 0);
        return result;
    }
}
