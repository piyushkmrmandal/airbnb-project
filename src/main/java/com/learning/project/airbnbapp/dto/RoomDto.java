package com.learning.project.airbnbapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.project.airbnbapp.entity.Hotel;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomDto {
    Long id;
    @JsonIgnore
    Hotel hotel;
    String type;
    BigDecimal basePrice;
    String[] photos;
    String[] amenities;
    Integer totalCount;
    Integer capacity;
}
