package com.learning.project.airbnbapp.service;

import com.learning.project.airbnbapp.entity.Room;

public interface InventoryService {
    void initializeRoomForAYear(Room room);
    void deleteFutureInventories(Room room);
}
