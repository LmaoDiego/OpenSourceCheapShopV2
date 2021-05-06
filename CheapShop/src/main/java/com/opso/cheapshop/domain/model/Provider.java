package com.opso.cheapshop.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Provider")
public class Provider extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    private List<Product> recommendations;


    public Provider(long id, String name) {
        this.id = id;
        this.name = name;
        this.recommendations= new ArrayList<>();
    }

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void AddRecommendation(Product product){
        this.recommendations.add(product);
    }


}
