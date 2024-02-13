package com.mprzys.projekt.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppUserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUserDatabase appUserDatabase;

    public void setAppUser(AppUserDatabase appUserDatabase) {
        this.appUserDatabase = appUserDatabase;
    }
}
