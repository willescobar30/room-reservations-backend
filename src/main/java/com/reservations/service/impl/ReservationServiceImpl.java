package com.reservations.service.impl;

import com.reservations.model.Reservation;
import com.reservations.repo.IReservationRepo;
import com.reservations.repo.IRoomRepo;
import com.reservations.service.IReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepo repo;
    private final IRoomRepo roomRepo;

    @Override
    public Reservation save(Reservation reservation) throws Exception {
        // 1. Verificar que la habitación existe
        var roomOpt = roomRepo.findById(reservation.getRoom().getIdRoom());
        if (roomOpt.isEmpty()) {
            throw new Exception("The Room does not exist");
        }

        var room = roomOpt.get();

        // 2. Verificar disponibilidad
        if (!room.isRoomAvailable()) {
            throw new Exception("The Room is not currently available");
        }

        // 3. Verificar conflictos de reserva
        List<Reservation> conflictingReservations = repo.findConflictReservations(
                room.getIdRoom(),
                reservation.getCheckInDate(),
                reservation.getCheckOutDate()
        );

        if (!conflictingReservations.isEmpty()) {
            throw new Exception("There is a reservation for the selected room in the requested range dates");
        }

        // 4. Guardar la reserva
        Reservation saved = repo.save(reservation);

        // 5. (Opcional) Marcar habitación como no disponible
        room.setRoomAvailable(false);
        roomRepo.save(room);

        return saved;

    }

    @Override
    public Reservation update(Reservation reservation, Integer id) throws Exception {
        reservation.setIdReservation(id);
        return repo.save(reservation);
    }

    @Override
    public List<Reservation> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Reservation findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Reservation());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}
