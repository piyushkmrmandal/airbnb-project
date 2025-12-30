package com.learning.project.airbnbapp.entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Embeddable
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelContactInfo {
    String address;
    String phoneNumber;
    String email;
    String location;
}
