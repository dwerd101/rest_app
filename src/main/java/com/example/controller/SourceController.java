package com.example.controller;


import com.example.model.Source;
import com.example.service.SourceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SourceController {

    private final SourceService sourceService;

    public SourceController(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @GetMapping("/source")
    public List<Source> findAllSource() {
        return sourceService.findAll();
    }
}
