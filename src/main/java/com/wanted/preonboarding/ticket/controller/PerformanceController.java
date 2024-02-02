package com.wanted.preonboarding.ticket.controller;

import com.wanted.preonboarding.core.domain.response.ResponseHandler;
import com.wanted.preonboarding.ticket.dto.request.PerformanceSearchRequest;
import com.wanted.preonboarding.ticket.dto.response.PerformanceResponse;
import com.wanted.preonboarding.ticket.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/performance")
@RestController
public class PerformanceController {
    private final PerformanceService performanceService;

    @GetMapping
    public ResponseEntity<ResponseHandler<Page<PerformanceResponse>>> findAllPerformance(PerformanceSearchRequest request, Pageable pageable) {
        return null;
    }
}
