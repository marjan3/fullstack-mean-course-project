package com.linkedin.learning.linkedinfullstackapp.rest;

import com.linkedin.learning.linkedinfullstackapp.converter.RoomEntityToReservableRoomResponseConverter;
import com.linkedin.learning.linkedinfullstackapp.entity.ReservationEntity;
import com.linkedin.learning.linkedinfullstackapp.entity.RoomEntity;
import com.linkedin.learning.linkedinfullstackapp.model.response.ReservableRoomResponse;
import com.linkedin.learning.linkedinfullstackapp.model.request.ReservationRequest;
import com.linkedin.learning.linkedinfullstackapp.model.response.ReservationResponse;
import com.linkedin.learning.linkedinfullstackapp.repository.PageableRoomRepository;
import com.linkedin.learning.linkedinfullstackapp.repository.ReservationRepository;
import com.linkedin.learning.linkedinfullstackapp.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(ResourceConstants.ROOM_RESERVATION_V1)
@CrossOrigin
public class ReservationResource {

    @Autowired
    PageableRoomRepository pageableRoomRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    RoomRepository   roomRepository;

    @Autowired
    ConversionService conversionService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public Page<ReservableRoomResponse> getAvailableRooms(
            @RequestParam(value="checkin")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate checkin,
            @RequestParam(value="checkout")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate checkout, Pageable pageable){

        System.out.println("Called getAvailableRooms...");
        Page<RoomEntity> roomEntities = pageableRoomRepository.findAll(pageable);

        return roomEntities.map(new RoomEntityToReservableRoomResponseConverter());

    }

    @GetMapping(path="/{roomId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public ResponseEntity<RoomEntity> getRoomById(@PathVariable Long roomId){

        RoomEntity roomEntity = roomRepository.findById(roomId);

        return new ResponseEntity<>(roomEntity, HttpStatus.OK);

    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody ReservationRequest reservationRequest){

        ReservationEntity reservationEntity = conversionService.convert(reservationRequest, ReservationEntity.class);
        reservationRepository.save(reservationEntity);
        RoomEntity roomEntity = roomRepository.findById(reservationRequest.getRoomId());
        roomEntity.addReservationEntity(reservationEntity);
        roomRepository.save(roomEntity);

        ReservationResponse reservationResponse = conversionService.convert(reservationEntity, ReservationResponse.class);

        return new ResponseEntity<>(reservationResponse, HttpStatus.CREATED);
    }

    @PutMapping(
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ReservableRoomResponse> updateReservation(@RequestBody ReservationRequest reservationRequest){
        return new ResponseEntity<>(new ReservableRoomResponse(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable long reservationId){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
