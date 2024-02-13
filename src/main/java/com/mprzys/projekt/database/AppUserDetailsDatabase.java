package com.mprzys.projekt.database;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AppUserDetailsDatabase {

    @GeneratedValue
    @Id
    private Long id;

    @OneToOne
    private AppUserDatabase user;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

}
