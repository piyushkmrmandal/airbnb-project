package com.learning.project.airbnbapp.controller;

import com.learning.project.airbnbapp.dto.HotelDto;
import com.learning.project.airbnbapp.service.HotelService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/hotels")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HotelController {

    HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelDto> createNewHotel(@RequestBody HotelDto hotelDto) {
        log.info("Attempting to create hotel with name :: " + hotelDto.getName());
        HotelDto hotel = hotelService.createNewHotel(hotelDto);
        return new ResponseEntity<>(hotel, HttpStatus.CREATED);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable Long hotelId) {
        log.info("Attempting to get hotel with id :: " + hotelId);
        HotelDto hotel = hotelService.getHotelById(hotelId);
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<HotelDto> updateHotelById(@PathVariable Long hotelId,
                                                @RequestBody HotelDto hotelDto) {
        log.info("Attempting to update hotel with name :: " + hotelDto.getName());
        HotelDto hotel = hotelService.updateHotelById(hotelId, hotelDto);
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> deleteHotelById(@PathVariable Long hotelId) {
        log.info("Attempting to delete hotel with id :: " + hotelId);
        hotelService.deleteHotelById(hotelId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{hotelId}")
    public ResponseEntity<Void> activateHotelById(@PathVariable Long hotelId) {
        log.info("Attempting to activate hotel with id :: " + hotelId);
        hotelService.activateHotelById(hotelId);
        return ResponseEntity.noContent().build();
    }

}
