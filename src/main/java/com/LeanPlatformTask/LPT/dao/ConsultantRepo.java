package com.LeanPlatformTask.LPT.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.LeanPlatformTask.LPT.model.ConsultantModel;


public interface ConsultantRepo extends JpaRepository<ConsultantModel,Long>{
	
	@Query("SELECT c.id FROM ConsultantModel c WHERE c.areaOfExpertise = :areaOfExpertise AND c.experience >= :experience")
    List<Long> findConsultantIdsByAreaOfExpertiseAndExperience(
        @Param("areaOfExpertise") String areaOfExpertise,
        @Param("experience") Integer experience
    );
	
	boolean existsByEmail(String email);
}
