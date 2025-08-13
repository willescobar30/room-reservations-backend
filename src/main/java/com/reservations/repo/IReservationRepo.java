package com.reservations.repo;

import com.reservations.model.Reservation;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface IReservationRepo extends IGenericRepo<Reservation, Integer>{

    @Query("SELECT r FROM Reservation r WHERE r.room.idRoom = :roomId " +
            "AND r.checkOutDate > :checkIn AND r.checkInDate < :checkOut")
    List<Reservation> findConflictReservations(Integer roomId, LocalDateTime checkIn, LocalDateTime checkOut);
}
