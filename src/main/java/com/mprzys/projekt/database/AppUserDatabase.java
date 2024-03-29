package com.mprzys.projekt.database;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class AppUserDatabase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;

    @OneToOne(mappedBy = "appUserDatabase", cascade = CascadeType.ALL, orphanRemoval = true)
    private AppUserProfile appUserProfile;

}