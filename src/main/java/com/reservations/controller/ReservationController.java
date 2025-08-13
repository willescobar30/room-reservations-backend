package com.reservations.controller;

import com.reservations.dto.ReservationDTO;
import com.reservations.model.Reservation;
import com.reservations.service.IReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final IReservationService service;

    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;


    @GetMapping
    public ResponseEntity<List<ReservationDTO>> findAll() throws Exception{
        List<ReservationDTO> list = service.findAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Reservation> save(@Valid @RequestBody ReservationDTO dto) throws Exception{
        Reservation obj = service.save(modelMapper.map(dto, Reservation.class));
        return ResponseEntity.ok().body(modelMapper.map(obj, Reservation.class));
    }

    private Reservation convertToEntity(ReservationDTO dto){
        return modelMapper.map(dto, Reservation.class);
    }
    private ReservationDTO convertToDto(Reservation entity){
        return modelMapper.map(entity, ReservationDTO.class);
    }

}
