package com.mprzys.projekt.services;

import com.mprzys.projekt.repository.AppUserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AppUserDetailsService {

    private AppUserDetailsRepository appUserDetailsRepository;

    @Autowired
    public AppUserDetailsService(AppUserDetailsRepository appUserDetailsRepository) {
        this.appUserDetailsRepository = appUserDetailsRepository;
    }


}
