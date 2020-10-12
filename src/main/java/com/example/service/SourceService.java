package com.example.service;

import com.example.model.Source;
import com.example.repository.SourceRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceService {

  private SourceRep sourceRep;

    @Autowired
    public void setSourceRep(SourceRep sourceRep) {
        this.sourceRep = sourceRep;
    }

    public List<Source> findAll() {
        return sourceRep.findAll();
    }
}
