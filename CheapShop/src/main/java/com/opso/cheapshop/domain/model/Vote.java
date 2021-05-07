package com.opso.cheapshop.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(
        name = "votes"
)
public class Vote extends AuditModel {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @NotNull
    @Size(
            max = 100
    )
    @NaturalId
    private String name;

    public Vote() {
    }

    public Long getId() {
        return this.id;
    }

    public Vote setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Vote setName(String name) {
        this.name = name;
        return this;
    }
}
