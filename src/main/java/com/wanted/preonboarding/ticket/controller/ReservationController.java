package com.wanted.preonboarding.ticket.controller;

import com.wanted.preonboarding.core.domain.response.ResponseHandler;
import com.wanted.preonboarding.ticket.dto.ReservationInfo;
import com.wanted.preonboarding.ticket.dto.request.ReservationRequest;
import com.wanted.preonboarding.ticket.dto.response.ReservationResponse;
import com.wanted.preonboarding.ticket.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/reservation")
@RestController
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ResponseHandler<ReservationResponse>> reserve(@RequestBody ReservationRequest request) {
        ReservationInfo reservationInfo = reservationService.reserve(request.toDto());
        return ResponseEntity.ok(
                ResponseHandler.<ReservationResponse>builder()
                        .statusCode(HttpStatus.OK)
                        .message("Success")
                        .data(ReservationResponse.of(reservationInfo))
                        .build()
        );
    }
}
