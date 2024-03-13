package com.LeanPlatformTask.LPT.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.LeanPlatformTask.LPT.model.SlotModel;

public interface SlotRepo extends JpaRepository<SlotModel, Long>{
	
	List<SlotModel> findByDateAndStartTimeAndEndtime(LocalDate date, LocalTime startTime, LocalTime endTime);
	
	
	
	@Query("SELECT s FROM SlotModel s WHERE s.consultantId IN :consultantIds AND s.booked=false")
    List<SlotModel> findSlotsByConsultantIds(@Param("consultantIds") List<Long> consultantIds);

}
