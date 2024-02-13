package com.mprzys.projekt.pages;

import com.mprzys.projekt.database.AppUserDatabase;
import com.mprzys.projekt.database.AppUserProfile;
import com.mprzys.projekt.repository.AppUserProfileRepository;
import com.mprzys.projekt.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserProfileController {

    private final AppUserRepository appUserRepository;
    private final AppUserProfileRepository appUserProfileRepository;

    @Autowired
    public UserProfileController(AppUserRepository appUserRepository, AppUserProfileRepository appUserProfileRepository) {
        this.appUserRepository = appUserRepository;
        this.appUserProfileRepository = appUserProfileRepository;
    }

    @GetMapping("/user/profile")
    public String userProfileForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        AppUserDatabase user = appUserRepository.findByUsername(currentUserName)
                .orElseThrow(() -> new RuntimeException("User not found"));

        AppUserProfile profile = Optional.ofNullable(user.getAppUserProfile())
                .orElseGet(() -> {
                    AppUserProfile newProfile = new AppUserProfile();
                    newProfile.setAppUser(user);
                    appUserProfileRepository.save(newProfile);
                    user.setAppUserProfile(newProfile);
                    appUserRepository.save(user);
                    return newProfile;
                });

        model.addAttribute("profile", profile);
        return "userProfile";
    }


    @PostMapping("/user/updateProfile")
    public String updateUserProfile(AppUserProfile profile, Authentication authentication) {
        String currentUserName = authentication.getName();
        AppUserDatabase user = appUserRepository.findByUsername(currentUserName).orElse(null);

        if (user != null && user.getAppUserProfile() != null) {
            AppUserProfile existingProfile = user.getAppUserProfile();
            existingProfile.setFirstName(profile.getFirstName());
            existingProfile.setLastName(profile.getLastName());
            existingProfile.setEmail(profile.getEmail());
            existingProfile.setPhoneNumber(profile.getPhoneNumber());

            appUserProfileRepository.save(existingProfile);
            return "redirect:/user/profile";
        }

        // Jeśli użytkownik nie istnieje, przekieruj do strony błędu
        return "redirect:/error";
    }
}