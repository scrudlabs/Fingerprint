package com.fingermatch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fingermatch.domain.UserElectronicId;

/**
 * Spring Data JPA repository for the User entity.
 */
@Repository
public interface InfosRepository extends JpaRepository<UserElectronicId, Long> {

	List<UserElectronicId> findAll();
}
