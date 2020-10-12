package com.example.controller;

import com.example.log.LoggingMethod;
import com.example.model.ProfileResultView;
import com.example.model.dto.ProfileResultDto;
import com.example.service.ProfileResultService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class ProfileResultController {

    private final ProfileResultService profileResultService;

    public ProfileResultController(ProfileResultService profileResultService) {
        this.profileResultService = profileResultService;
    }

    @GetMapping(value = "{id}/profileResult", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<ProfileResultDto>> getProfileResult(@PathVariable("id") Long standId,
                                                            @RequestParam(required = false, defaultValue = "0") int page,
                                                            @RequestParam(required = false, defaultValue = "20") int size) {
        if (size == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            Pageable pageable = PageRequest.of(page, size);
            Page<ProfileResultDto> profileResults = profileResultService.findAllByProfileTaskId(standId,pageable);
            return new ResponseEntity<>(profileResults, HttpStatus.OK);
        }
    }



    @LoggingMethod
    @PutMapping(value = "profileResult/update", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity changeProfileResult(@RequestBody List<ProfileResultDto> profileResultViewList) {
         profileResultService.updateAll(profileResultViewList);
         return new ResponseEntity<>(HttpStatus.OK);
    }




}
