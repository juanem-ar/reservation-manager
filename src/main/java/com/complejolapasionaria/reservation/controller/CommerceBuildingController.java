package com.complejolapasionaria.reservation.controller;

import com.complejolapasionaria.reservation.dto.CommerceBuildingRequestDto;
import com.complejolapasionaria.reservation.dto.CommerceBuildingResponseDto;
import com.complejolapasionaria.reservation.service.ICommerceBuildingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/commerce-buildings")
@SecurityRequirement(name="Bearer Authentication")
public class CommerceBuildingController {

    private final ICommerceBuildingService iCommerceBuildingService;

    public CommerceBuildingController(ICommerceBuildingService iCommerceBuildingService) {
        this.iCommerceBuildingService = iCommerceBuildingService;
    }

    @PostMapping("/save")
    @Secured(value = {"ROLE_ADMIN"})
    public ResponseEntity<CommerceBuildingResponseDto> saveCommerceBuilding(@Valid @RequestBody CommerceBuildingRequestDto dto, Authentication authentication) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(iCommerceBuildingService.save(dto,authentication));
    }

    @GetMapping("/{id}")
    @Secured(value = {"ROLE_USER","ROLE_ADMIN"})
    public ResponseEntity<Void> getCommerceBuilding(@PathVariable Long id){
        return null;
    }

    @GetMapping
    @Secured(value = {"ROLE_ADMIN"})
    public ResponseEntity<List<Void>> getAllCommerceBuildings(){
        return null;
    }

    @PatchMapping("/{id}")
    @Secured(value = {"ROLE_USER"})
    public ResponseEntity<Void> updateCommerceBuilding(@PathVariable Long id){
        return null;
    }

    @DeleteMapping("/{id}")
    @Secured(value = {"ROLE_USER","ROLE_ADMIN"})
    public void removeCommerceBuilding(@PathVariable Long id){}

}
