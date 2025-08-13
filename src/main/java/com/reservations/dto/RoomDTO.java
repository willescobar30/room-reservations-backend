package com.reservations.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RoomDTO {
    private Integer idRoom;
    @NotNull
    private String roomNumber;
    @NotNull
    private String roomType;
    @NotNull
    @DecimalMin(value ="0.0", inclusive = false)
    private BigDecimal price;
    @NotNull
    private boolean roomAvailable;
}
