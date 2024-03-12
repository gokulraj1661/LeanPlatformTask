package com.LeanPlatformTask.LPT.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.LeanPlatformTask.LPT.dao.ConsultantRepo;
import com.LeanPlatformTask.LPT.dao.SlotRepo;
import com.LeanPlatformTask.LPT.model.ConsultantModel;
import com.LeanPlatformTask.LPT.model.SlotModel;

@RestController
public class SlotController {
	@Autowired
	SlotRepo slotRepository;
	@Autowired
	ConsultantRepo consultantRepository;
	@PostMapping("createslot")
	public String createSlot(@RequestBody SlotModel slotRequest) {
		Long consultantId = slotRequest.getConsultantId();
		LocalDate slotDate=slotRequest.getDate();
		LocalTime startTime=slotRequest.getStartTime();
		LocalTime endtime=slotRequest.getEndtime();
		
		
		if(consultantRepository.existsById(consultantId)) {
			if (!slotRepository.existsByDateAndStartTimeAndEndtimeAndConsultantId(slotDate, startTime, endtime,consultantId)) {
				
			    SlotModel slot=new SlotModel();
			    slot.setDate(slotRequest.getDate());
			    slot.setStartTime(slotRequest.getStartTime());
			    slot.setEndtime(slotRequest.getEndtime());
			    slot.setBooked(slotRequest.isBooked());
			    slot.setConsultantId(slotRequest.getConsultantId());
			    slotRepository.save(slot);
			    return "Slot created successfully";
			}
			return "Slot already exists";
			
		}else {
			return "Consultant not found";
		}
	}
	@GetMapping("searchconsultant")
    public List<SlotModel> searchConsultant(
            @RequestParam(name = "areaOfExpertise", required = false) String areaOfExpertise,
            @RequestParam(name = "experience", required = false) Integer experience) {

        if (areaOfExpertise != null && experience != null) {
            List<Long> consultantIds = consultantRepository.findConsultantIdsByAreaOfExpertiseAndExperience(areaOfExpertise, experience);
            return slotRepository.findSlotsByConsultantIds(consultantIds);
        }else {
            return null; 
        }
    }
}
