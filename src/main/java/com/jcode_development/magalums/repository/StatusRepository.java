package com.jcode_development.magalums.repository;

import com.jcode_development.magalums.model.status.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
	
	@Query("SELECT s FROM Status s WHERE s.id =: id")
	Status findByStatusId(@Param("id") Long id);
}
