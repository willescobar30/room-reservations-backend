package com.reservations.exception;

import java.time.LocalDateTime;
public record CustomErrorRecord(
        //fecha del error
        LocalDateTime datetime,
        //mensaje de error
        String message,
        //detalles del error
        String details

) {


}
