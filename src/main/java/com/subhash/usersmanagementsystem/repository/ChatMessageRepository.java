//package com.phegondev.usersmanagementsystem.repository;
//
//
//import com.phegondev.usersmanagementsystem.entity.ChatMessage;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
////    List<ChatMessage> findBySenderOrReceiverOrderByTimestampAsc(String sender, String receiver, String receiver2);
//
//    @Query("SELECT c FROM ChatMessage c WHERE LOWER(c.sender) = LOWER(:admin) OR LOWER(c.receiver) = LOWER(:admin) ORDER BY c.timestamp ASC")
//    List<ChatMessage> findAllChatsForAdmin(@Param("admin") String admin);
//
//
//    @Query("SELECT c FROM ChatMessage c WHERE (c.sender = :user AND c.receiver = :admin) OR (c.sender = :admin AND c.receiver = :user) ORDER BY c.timestamp ASC")
//    List<ChatMessage> findChatBetweenUserAndAdmin(@Param("user") String user, @Param("admin") String admin);
//
//
//}

package com.subhash.usersmanagementsystem.repository;

import com.subhash.usersmanagementsystem.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    @Query("SELECT c FROM ChatMessage c WHERE LOWER(c.sender) = LOWER(:admin) OR LOWER(c.receiver) = LOWER(:admin) ORDER BY c.timestamp ASC")
    List<ChatMessage> findAllChatsForAdmin(@Param("admin") String admin);

    @Query("SELECT c FROM ChatMessage c WHERE (LOWER(c.sender) = LOWER(:user) AND LOWER(c.receiver) = LOWER(:admin)) OR (LOWER(c.sender) = LOWER(:admin) AND LOWER(c.receiver) = LOWER(:user)) ORDER BY c.timestamp ASC")
    List<ChatMessage> findChatBetweenUserAndAdmin(@Param("user") String user, @Param("admin") String admin);
}

