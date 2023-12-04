package com.example.sdmisoback.security.user;

import java.security.Principal;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sdmisoback.dto.AttachmentFileView;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService service;

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(
          @RequestBody ChangePasswordRequest request,
          Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/recently-viewed")
    public ResponseEntity<?> addRecentlyViewed(
        @RequestBody Integer fileId,
        Principal connectedUser
    ) {
        service.addFileToRecentlyViewed(connectedUser, fileId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/recently-viewed")
    public ResponseEntity<?> getRecentlyViewed(
        Principal connectedUser
    ) {
        try {
            List<AttachmentFileView> files = service.getRecentlyViewedFiles(connectedUser);
            if (files.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No recently viewed files found for the user");
            }
            return ResponseEntity.ok(files);
        } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("An error occurred while processing the request");
        }
    }
}