package com.example.controller;


import com.example.model.ProfileTaskView;
import com.example.model.dto.ProfileTaskDto;
import com.example.service.ProfileTaskService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.*;

import org.springframework.web.bind.annotation.*;


@RestController
@Log4j2
@RequestMapping("/task")
public class ProfileTaskController {


    private final ProfileTaskService profileTaskViewService;

    public ProfileTaskController(ProfileTaskService profileTaskViewService) {
        this.profileTaskViewService = profileTaskViewService;
    }

    @GetMapping("{id}/profileTask")
    public Page<ProfileTaskDto> findAllByProfileTaskView(@RequestParam(value = "domain", required = false) String domain,
                                                         @RequestParam(value = "tableName", required = false)
                                                                  String tableName,
                                                         @PathVariable("id") Long id, Pageable pageable) {
        if(domain==null&&tableName==null) {
            return profileTaskViewService.findBySearch(id,pageable);
        }
        else if(domain!=null && tableName!=null) {
            return profileTaskViewService.findBySearch(id,domain,tableName,pageable);
        }
        else if(domain!=null) {
            return profileTaskViewService.findBySearch(id,domain, pageable);
        }
        else {
            return profileTaskViewService.findBySearch(id,tableName,pageable);
        }

    }


}


