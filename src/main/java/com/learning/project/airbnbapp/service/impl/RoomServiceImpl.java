package com.learning.project.airbnbapp.service.impl;

import com.learning.project.airbnbapp.dto.RoomDto;
import com.learning.project.airbnbapp.entity.Hotel;
import com.learning.project.airbnbapp.entity.Room;
import com.learning.project.airbnbapp.exception.ResourceNotFoundException;
import com.learning.project.airbnbapp.repository.HotelRepository;
import com.learning.project.airbnbapp.repository.RoomRepository;
import com.learning.project.airbnbapp.service.InventoryService;
import com.learning.project.airbnbapp.service.RoomService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoomServiceImpl implements RoomService {

    HotelRepository hotelRepository;
    RoomRepository roomRepository;
    ModelMapper modelMapper;

    InventoryService inventoryService;

    @Override
    public RoomDto createNewRoom(Long hotelId, RoomDto roomDto) {
        log.info("Attempting to create new room of type :: " + roomDto.getType() + ", in hotel with Id : " + hotelId);
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with id " + hotelId + " not found"));
        Room room = modelMapper.map(roomDto, Room.class);
        room.setHotel(hotel);
        room = roomRepository.save(room);
        if(Boolean.TRUE.equals(hotel.getActive()))
            inventoryService.initializeRoomForAYear(room);
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        log.info("Attempting to get all rooms in hotel with Id :: " + hotelId);
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with id " + hotelId + " not found"));
        return hotel.getRooms()
                .stream()
                .map(room -> modelMapper.map(room, RoomDto.class))
                .toList();
    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        log.info("Attempting to get room with Id :: " + roomId);
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room with id " + roomId + " not found"));
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    @Transactional
    public void deleteRoomById(Long roomId) {
        log.info("Attempting to delete room with Id :: " + roomId);
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room with id " + roomId + " not found"));
        inventoryService.deleteFutureInventories(room);
        roomRepository.deleteById(roomId);
    }
}
