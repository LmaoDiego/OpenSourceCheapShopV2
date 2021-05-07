package com.opso.cheapshop.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;


    public User() {    }

  public User(@NotNull String email, @NotNull String name) {
        this.email = email;
       this.name = name;
    }

    public Long getId()                 {   return id;   }

    public User setId(Long id)          {   this.id = id;   return this;}

    public String getEmail()            {  return email;  }

    public User setEmail(String email)  {   this.email = email;   return this; }

    public String getName()              {    return name;    }

    public User setName(String name)      {  this.name = name;   return this; }
}