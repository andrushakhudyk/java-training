package com.example.demo;

import org.springframework.stereotype.Service;
//import org.springframework.context.annotation.Profile;
import java.util.NoSuchElementException;
import java.time.LocalDate;
//import java.time.LocalDateTime;
import java.util.List;
//import java.util.Arrays;
import java.util.Map;

@Service
//@Profile("dev")
public class ReservationService{


    private final Map<Long, Reservation> reservationMap = Map.of(
        1L,
        new Reservation(
            1L,
            101L,
            11L,
            LocalDate.now().minusDays(7),
            LocalDate.now().plusDays(7),
            ReservationStatus.CANCELLED
        ),
        2L,
        new Reservation(
            2L,
            102L,
            12L,
            LocalDate.now().minusDays(1),
            LocalDate.now().plusDays(5),
            ReservationStatus.CANCELLED
        ),
        3L,
        new Reservation(
            3L,
            103L,
            13L,
            LocalDate.now(),
            LocalDate.now().plusDays(7),
            ReservationStatus.CANCELLED
        )
    );
    public Reservation getReservationById(
        Long id
    ){
        if(!reservationMap.containsKey(id)){
            throw new NoSuchElementException("Not foun reservation by id = " + id);
        }
        return reservationMap.get(id);
    }
    public List<Reservation> findAllReservation(){
        return reservationMap.values().stream().toList();
    }
}