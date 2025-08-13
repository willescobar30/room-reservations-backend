package com.reservations.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

    private Integer idReservation;
    @NotNull
    @Size(min = 3, max= 70, message = "{firstname.size}")
    private String customerName;
    @NotNull
    private LocalDateTime checkInDate;
    @NotNull
    private LocalDateTime checkOutDate;
    @NotNull
    private RoomDTO room;
}
