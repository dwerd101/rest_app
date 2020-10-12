package com.example.demo;

import com.example.model.Source;
import com.example.repository.SourceRep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


@DataJpaTest
@DisplayName("When running SourceServiceTest")
public class SourceServiceTest {

    List<Source> sourceList;
    Source source;
    Source source2;
    @Autowired
    SourceRep sourceRep;
    @Autowired
    TestEntityManager testEntityManager;

    @BeforeEach
    void init() {
        sourceList = new ArrayList<>();
        source = new Source("Ресурс 7");
        source2 = new Source( "Ресурс 8");
        sourceList.add(source);
        sourceList.add(source2);
        testEntityManager.persist(source);
        testEntityManager.persist(source2);
        testEntityManager.flush();
    }

    @Test
    @DisplayName("find all sources")
    void findAllMethod_ok() {
        List<Source> foundSourceList = sourceRep.findAll();
        assertEquals(foundSourceList,sourceList);
    }





}
