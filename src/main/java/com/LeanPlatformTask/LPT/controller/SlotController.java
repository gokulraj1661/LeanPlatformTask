package com.LeanPlatformTask.LPT.controller;

import java.time.LocalDate;


import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.LeanPlatformTask.LPT.dao.ConsultantRepo;
import com.LeanPlatformTask.LPT.dao.SlotRepo;
import com.LeanPlatformTask.LPT.model.SlotModel;

@RestController
public class SlotController {
	@Autowired
	SlotRepo slotRepository;
	@Autowired
	ConsultantRepo consultantRepository;
	@PostMapping("createslot")
	public ResponseEntity<String> createSlot(@RequestBody SlotModel slotRequest) {
		
		Long consultantId = slotRequest.getConsultantId();
		LocalDate slotDate = null;
		LocalTime startTime = null;
		LocalTime endtime = null;
		try {
	        slotDate = slotRequest.getDate();
	    } catch (DateTimeParseException e) {
	        return ResponseEntity.badRequest().body("Invalid date format. Please use yyyy-MM-dd format.");
	    }
		try {
		    startTime=slotRequest.getStartTime();
		    endtime=slotRequest.getEndtime();
		}catch (DateTimeParseException e) {
			return ResponseEntity.badRequest().body("Invalid time format. Please use HH:mm:ss format.");
		}
		
		if (consultantRepository.existsById(consultantId)) {
			List<SlotModel> existingSlots = slotRepository.findByDateAndStartTimeAndEndtime(slotDate, startTime, endtime);
		    boolean slotExistsWithSameConsultant = false;
		    for (SlotModel existingSlot : existingSlots) {
		    	if (existingSlot.getConsultantId().equals(consultantId)) {
		    		slotExistsWithSameConsultant = true;
		            break;
		        }
		    }
		    if (!slotExistsWithSameConsultant) {
		    	SlotModel slot = new SlotModel();
		        slot.setDate(slotRequest.getDate());
		        slot.setStartTime(slotRequest.getStartTime());
		        slot.setEndtime(slotRequest.getEndtime());
		        slot.setBooked(slotRequest.isBooked());
		        slot.setConsultantId(slotRequest.getConsultantId());
		        slotRepository.save(slot);
		    return ResponseEntity.ok("Slot created successfully");
		    }else {
			     return ResponseEntity.status(HttpStatus.CONFLICT).body("Slot already exists with a different consultant");
		    }
		} else {
		     return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consultant not found");
		}
	}
	@GetMapping("searchconsultant")
    public ResponseEntity<List<SlotModel>> searchConsultant(
            @RequestParam(name = "areaOfExpertise", required = false) String areaOfExpertise,
            @RequestParam(name = "experience", required = false) Integer experience) {

        if (areaOfExpertise != null && experience != null) {
            List<Long> consultantIds = consultantRepository.findConsultantIdsByAreaOfExpertiseAndExperience(areaOfExpertise, experience);
            List<SlotModel> slots = slotRepository.findSlotsByConsultantIds(consultantIds);
            return ResponseEntity.ok(slots);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); 
        }
    }
}
