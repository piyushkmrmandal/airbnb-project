package com.learning.project.airbnbapp.dto;

import com.learning.project.airbnbapp.entity.HotelContactInfo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelDto {
    Long id;
    String name;
    String city;
    String[] photos;
    String[] amenities;
    HotelContactInfo contactInfo;
    Boolean active;
}
