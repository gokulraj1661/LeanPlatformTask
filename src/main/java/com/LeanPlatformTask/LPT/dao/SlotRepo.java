package com.LeanPlatformTask.LPT.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.LeanPlatformTask.LPT.model.SlotModel;

public interface SlotRepo extends JpaRepository<SlotModel, Long>{
	@Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM SlotModel s WHERE s.date = :date AND s.startTime = :startTime AND s.endtime = :endtime AND s.consultantId = :consultantId")
    boolean existsByDateAndStartTimeAndEndtimeAndConsultantId(
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endtime") LocalTime endtime,
            @Param("consultantId") Long consultantId
    );
	
	@Query("SELECT s FROM SlotModel s WHERE s.consultantId IN :consultantIds AND s.booked=false")
    List<SlotModel> findSlotsByConsultantIds(@Param("consultantIds") List<Long> consultantIds);

}
