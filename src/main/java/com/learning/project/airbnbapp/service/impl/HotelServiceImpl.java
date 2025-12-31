package com.learning.project.airbnbapp.service.impl;

import com.learning.project.airbnbapp.dto.HotelDto;
import com.learning.project.airbnbapp.entity.Hotel;
import com.learning.project.airbnbapp.entity.Room;
import com.learning.project.airbnbapp.exception.ResourceNotFoundException;
import com.learning.project.airbnbapp.repository.HotelRepository;
import com.learning.project.airbnbapp.service.HotelService;
import com.learning.project.airbnbapp.service.InventoryService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HotelServiceImpl implements HotelService {

    HotelRepository hotelRepository;
    ModelMapper modelMapper;

    InventoryService inventoryService;

    @Override
    public HotelDto createNewHotel(com.learning.project.airbnbapp.dto.HotelDto hotelDto) {
        log.info("Creating hotel with name {}", hotelDto.getName());
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        hotel.setActive(false);
        hotel = hotelRepository.save(hotel);
        log.info("Hotel with id :: {} has been created", hotel.getId());
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info("Getting hotel with id  :: {}", id);
        Hotel hotel =  hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with id :: " + id + " not found"));
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {
        log.info("Updating hotel with id  :: {}", id);
        Hotel hotel =  hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with id :: " + id + " not found"));
        modelMapper.map(hotelDto, hotel);
        hotel.setId(id);
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    @Transactional
    public void deleteHotelById(Long id) {
        Hotel hotel =  hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with id :: " + id + " not found"));
        hotelRepository.deleteById(id);
        for(Room room : hotel.getRooms())
            inventoryService.deleteFutureInventories(room);
    }

    @Override
    @Transactional
    public void activateHotelById(Long id) {
        log.info("Activating hotel with id  :: {}", id);
        Hotel hotel =  hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with id :: " + id + " not found"));
        hotel.setActive(true);
        //assuming only do it once
        for(Room room : hotel.getRooms()) {
            inventoryService.initializeRoomForAYear(room);
        }
    }


}
