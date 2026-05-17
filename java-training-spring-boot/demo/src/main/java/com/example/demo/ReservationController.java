package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;


@RestController
@RequestMapping("/api/reserv")
public class ReservationController{

    private static final Logger log = LoggerFactory.getLogger(ReservationController.class);

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(
            @PathVariable("id") Long id
        ){
        log.info("Calld getReservationById: id = " + id);
        return reservationService.getReservationById(id);
    }
    
    @GetMapping()
    public List<Reservation> getAllReservations(){
        log.info("Called getAllResrvations");
        return reservationService.findAllReservation();
    }

}