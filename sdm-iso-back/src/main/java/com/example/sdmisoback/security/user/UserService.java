package com.example.sdmisoback.security.user;

import java.security.Principal;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.sdmisoback.dto.AttachmentFileView;
import com.example.sdmisoback.repository.FiltersRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository user_repo;
    private final FiltersRepo file_repo;
    
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        user_repo.save(user);
    }

    public void addFileToRecentlyViewed(Principal connectedUser, Integer fileId) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        
        List<Integer> recentlyViewed = user.getRecentlyViewed();
        recentlyViewed.add(fileId);

        // Keep only the latest 10 files
        if (recentlyViewed.size() > 10) {
            recentlyViewed = recentlyViewed.subList(recentlyViewed.size() - 10, recentlyViewed.size());
        }

        user.setRecentlyViewed(recentlyViewed);
        user_repo.save(user);
    }

    public List<AttachmentFileView> getRecentlyViewedFiles(Principal connectedUser){
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        List<Integer> recentlyViewed = user.getRecentlyViewed();
        return file_repo.getRecentlyViewed(recentlyViewed);
    }
}