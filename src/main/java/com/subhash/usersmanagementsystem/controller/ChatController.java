package com.subhash.usersmanagementsystem.controller;

import com.subhash.usersmanagementsystem.entity.ChatMessage;
import com.subhash.usersmanagementsystem.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

//    @PostMapping("/send")
//    public ChatMessage sendMessage(@RequestParam String sender, @RequestParam String message) {
//        return chatService.sendMessage(sender, message);
//    }

    @PostMapping("/send")
    public ChatMessage sendMessage(@AuthenticationPrincipal UserDetails userDetails,
                                   @RequestBody ChatMessage request) {
        if (userDetails == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }

        String sender = userDetails.getUsername(); // Automatically detect logged-in user
        return chatService.sendMessage(sender, request.getMessage());
    }




    @PostMapping("/admin/reply")
    public ChatMessage sendAdminResponse(@RequestParam String user, @RequestParam String message) {
        return chatService.sendAdminResponse(user, message);
    }

//    @GetMapping("/history")
//    public List<ChatMessage> getChatHistory(@AuthenticationPrincipal UserDetails userDetails,
//                                            @RequestParam(required = false) String user) {
//        if (userDetails == null) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
//        }
//
//        String loggedInUser = userDetails.getUsername().toUpperCase(); // Normalize username
//        System.out.println("Logged in user: " + loggedInUser); // Debugging line
//
//        boolean isAdmin = loggedInUser.equalsIgnoreCase("ADMIN") || loggedInUser.equalsIgnoreCase("sy7795542@gmail.com");
//        System.out.println("Is Admin: " + isAdmin); // Debugging line
//
//        if (isAdmin) {
//            if (user == null || user.trim().isEmpty()) {
//                System.out.println("Fetching all messages for ADMIN...");
//                return chatService.getAllMessagesForAdmin();  // New method
//            } else {
//                System.out.println("Fetching messages between ADMIN and " + user);
//                return chatService.getChatHistory(user);
//            }
//        } else {
//            System.out.println("Fetching messages for normal user: " + loggedInUser);
//            return chatService.getChatHistory(loggedInUser);
//        }
//    }

    @GetMapping("/history")
    public List<ChatMessage> getChatHistory(@AuthenticationPrincipal UserDetails userDetails,
                                            @RequestParam(required = false) String user) {
        if (userDetails == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }

        String loggedInUser = userDetails.getUsername();
        boolean isAdmin = userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ADMIN"));

        System.out.println("Logged in user: " + loggedInUser);
        System.out.println("Is Admin: " + isAdmin);

        if (isAdmin) {
            if (user == null || user.trim().isEmpty()) {
                System.out.println("Fetching all messages for ADMIN...");
                return chatService.getAllMessagesForAdmin();  // New method
            } else {
                System.out.println("Fetching messages between ADMIN and " + user);
                return chatService.getChatHistory(user);
            }
        } else {
            System.out.println("Fetching messages for normal user: " + loggedInUser);
            return chatService.getChatHistory(loggedInUser);
        }
    }

}

