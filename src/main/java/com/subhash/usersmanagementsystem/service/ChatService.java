//package com.phegondev.usersmanagementsystem.service;
//
//import com.phegondev.usersmanagementsystem.entity.ChatMessage;
//import com.phegondev.usersmanagementsystem.repository.ChatMessageRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class ChatService {
//
//    @Autowired
//    private ChatMessageRepository chatMessageRepository;
//
//    public ChatMessage sendMessage(String sender, String message) {
//        ChatMessage chatMessage = ChatMessage.builder()
//                .sender(sender)
//                .receiver("ADMIN")
//                .message(message)
//                .timestamp(new Date())
//                .build();
//        return chatMessageRepository.save(chatMessage);
//    }
//
//    public ChatMessage sendAdminResponse(String user, String message) {
//        ChatMessage chatMessage = ChatMessage.builder()
//                .sender("ADMIN")
//                .receiver(user)
//                .message(message)
//                .timestamp(new Date())
//                .build();
//        return chatMessageRepository.save(chatMessage);
//    }
//
//    public List<ChatMessage> getChatHistory(String user) {
//        if ("ADMIN".equalsIgnoreCase(user.trim())) {
//            // Fetch all messages where ADMIN is involved
//            return chatMessageRepository.findAllChatsForAdmin("ADMIN");
//        } else {
//            // Fetch only messages between user and admin
//            return chatMessageRepository.findChatBetweenUserAndAdmin(user, "ADMIN");
//        }
//    }
//}
//

package com.subhash.usersmanagementsystem.service;

import com.subhash.usersmanagementsystem.entity.ChatMessage;
import com.subhash.usersmanagementsystem.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public ChatMessage sendMessage(String sender, String message) {
        ChatMessage chatMessage = ChatMessage.builder()
                .sender(sender)
                .receiver("ADMIN")
                .message(message)
                .timestamp(new Date())
                .build();
        return chatMessageRepository.save(chatMessage);
    }

    public ChatMessage sendAdminResponse(String user, String message) {
        ChatMessage chatMessage = ChatMessage.builder()
                .sender("ADMIN")
                .receiver(user)
                .message(message)
                .timestamp(new Date())
                .build();
        return chatMessageRepository.save(chatMessage);
    }

//    public List<ChatMessage> getChatHistory(String user) {
//        if ("ADMIN".equalsIgnoreCase(user.trim())) {
//            return chatMessageRepository.findAllChatsForAdmin("ADMIN");
//        } else {
//            return chatMessageRepository.findChatBetweenUserAndAdmin(user, "ADMIN");
//        }
//    }

    public List<ChatMessage> getChatHistory(String user) {

            return chatMessageRepository.findChatBetweenUserAndAdmin(user, "ADMIN");
    }

    public List<ChatMessage> getAllMessagesForAdmin() {
        return chatMessageRepository.findAllChatsForAdmin("ADMIN");
    }


}

