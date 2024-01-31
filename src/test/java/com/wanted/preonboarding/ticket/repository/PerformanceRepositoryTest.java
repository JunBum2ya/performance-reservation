package com.wanted.preonboarding.ticket.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("testdb")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("[Model] Performance")
@DataJpaTest
public class PerformanceRepositoryTest {

    @Autowired
    private PerformanceRepository performanceRepository;

    @Test
    public void givenPerformance_whenSavingPerformance_thenReturnsPerformance() {
        
    }
}
