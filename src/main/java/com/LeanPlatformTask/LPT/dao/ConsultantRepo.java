package com.LeanPlatformTask.LPT.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.LeanPlatformTask.LPT.model.ConsultantModel;
import com.LeanPlatformTask.LPT.model.SlotModel;

public interface ConsultantRepo extends JpaRepository<ConsultantModel,Long>{
	//List<ConsultantModel> findByAreaOfExpertiseAndExperience(String areaOfExpertise, Integer experience);
	@Query("SELECT c.id FROM ConsultantModel c WHERE c.areaOfExpertise = :areaOfExpertise AND c.experience = :experience")
    List<Long> findConsultantIdsByAreaOfExpertiseAndExperience(
        @Param("areaOfExpertise") String areaOfExpertise,
        @Param("experience") Integer experience
    );
}
