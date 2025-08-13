package com.reservations.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idRoom;
    @Column(nullable = false, length = 10, unique = true)
    private String roomNumber;
    @Column(nullable = false, length = 70)
    private String roomType;
    @Column(nullable = false, length = 10)
    private BigDecimal price;
    @Column(nullable = false)
    private boolean roomAvailable;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Reservation> reservation;
}
