package com.jcode_development.magalums.repository;

import com.jcode_development.magalums.model.notification.Notification;
import com.jcode_development.magalums.model.status.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, String> {

    @Query("SELECT n FROM Notification n WHERE (n.status = :status1 OR n.status = :status2) AND n.dateTime <= :date")
    List<Notification> findNotificationsByStatusAndDateTime(@Param("status1") Status status1, @Param("status2") Status status2, @Param("date") LocalDateTime date);

}
