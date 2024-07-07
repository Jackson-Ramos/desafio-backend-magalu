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
	
	/*
	@Query("SELECT n FROM Notification n WHERE (n.status =: pendingId OR n.status =: errorId) AND n.dateTime <= : CURRENT_TIMESTAMP")
	List<Notification> findNotificationsByStatusAndDateTime(@Param("pendingId") Long pendingId, @Param("errorId") Long errorId);
	 */
	
	@Query("SELECT n FROM Notification n WHERE (n.status.description = :status1 OR n.status.description = :status2) AND n.dateTime <= :currentDateTime")
	List<Notification> findNotificationsByStatusAndDateTime(@Param("status1") String status1, @Param("status2") String status2, @Param("currentDateTime") LocalDateTime currentDateTime);
	
	
	List<Notification> findByStatusInAndDateTimeBefore(List<Status> status, LocalDateTime dateTime);
	
}
