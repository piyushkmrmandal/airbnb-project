package com.learning.project.airbnbapp.service;

import com.learning.project.airbnbapp.dto.HotelDto;

public interface HotelService {
    HotelDto createNewHotel(com.learning.project.airbnbapp.dto.HotelDto hotelDto);
    HotelDto getHotelById(Long id);
    HotelDto updateHotelById(Long id, HotelDto hotelDto);
    void deleteHotelById(Long id);
    void activateHotelById(Long id);
}
