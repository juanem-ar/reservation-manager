package com.reservation.manager.controller;

import com.reservation.manager.dto.ReservationRequestDto;
import com.reservation.manager.dto.ReservationResponseDto;
import com.reservation.manager.service.IReservationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
@SecurityRequirement(name="Bearer Authentication")
@RequiredArgsConstructor
public class ReservationController {

    private final IReservationService iReservationService;

    @PostMapping("/create/rental-units/{id}")
    @Secured(value = {"ROLE_USER"})
    public ResponseEntity<ReservationResponseDto> reservationCreateByUser(
            @Validated @RequestBody ReservationRequestDto dto,
            @PathVariable Long id,
            Authentication authentication) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(iReservationService.userReserve(dto, authentication, id));
    }

    @PostMapping("/create/rental-units/{id}/users/{userId}")
    @Secured(value = {"ROLE_ADMIN"})
    public ResponseEntity<ReservationResponseDto> reservationCreateByAdmin(
            @Validated @RequestBody ReservationRequestDto dto,
            Authentication authentication,
            @PathVariable Long id,
            @PathVariable Long userId) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(iReservationService.adminReserve(dto,authentication, userId, id));
    }

    @GetMapping("/{id}")
    @Secured(value = {"ROLE_USER","ROLE_ADMIN"})
    public ResponseEntity<ReservationResponseDto> getReservationById(@PathVariable Long id, Authentication authentication)throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(iReservationService.getById(id, authentication));
    }

    @PatchMapping("/{id}/users/{userId}")
    @Secured(value = {"ROLE_ADMIN"})
    public ResponseEntity<ReservationResponseDto> updateReservationByAdmin(@Validated @RequestBody ReservationRequestDto dto,
                                                                    @PathVariable Long id,
                                                                    @PathVariable Long userId,
                                                                    Authentication authentication) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(iReservationService.update(dto, id, userId, authentication));
    }
    @DeleteMapping("/{id}")
    @Secured(value = {"ROLE_ADMIN"})
    public ResponseEntity<String> removeReservationByAdmin(@PathVariable Long id, Authentication authentication) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(iReservationService.removeReservation(id, authentication));
    }
    @PatchMapping("/confirm/{id}")
    @Secured(value = {"ROLE_ADMIN"})
    public ResponseEntity<String> confirmReservationByAdmin(@PathVariable Long id, Authentication authentication) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(iReservationService.confirmReservation(id, authentication));
    }
    @PatchMapping("/confirm-mp-payment/{id}")
    public ResponseEntity<String> confirmReservationByMp(@PathVariable Long id,
                                                         @RequestParam(required = false) String collection_id,
                                                         @RequestParam(required = false) String collection_status,
                                                         @RequestParam(required = false) String payment_id,
                                                         @RequestParam(required = false) String status,
                                                         @RequestParam(required = false) String external_reference,
                                                         @RequestParam(required = false) String payment_type,
                                                         @RequestParam(required = false) String merchant_order_id,
                                                         @RequestParam(required = false) String preference_id,
                                                         @RequestParam(required = false) String site_id,
                                                         @RequestParam(required = false) String processing_mode,
                                                         @RequestParam(required = false) String merchant_account_id) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(iReservationService.confirmReservation(id, collection_status, status));
    }
}