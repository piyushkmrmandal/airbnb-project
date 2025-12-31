package com.learning.project.airbnbapp.service.impl;

import com.learning.project.airbnbapp.entity.Inventory;
import com.learning.project.airbnbapp.entity.Room;
import com.learning.project.airbnbapp.repository.InventoryRepository;
import com.learning.project.airbnbapp.service.InventoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InventoryServiceImpl implements InventoryService {

    InventoryRepository inventoryRepository;
    ModelMapper modelMapper;

    @Override
    public void initializeRoomForAYear(Room room) {
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusYears(1);
        for(; !today.isAfter(endDate); today = today.plusDays(1)) {
            Inventory inventory = Inventory.builder()
                    .hotel(room.getHotel())
                    .room(room)
                    .bookedCount(0)
                    .city(room.getHotel().getCity())
                    .date(today)
                    .price(room.getBasePrice())
                    .surgeFactor(BigDecimal.ONE)
                    .totalCount(room.getTotalCount())
                    .closed(false)
                    .build();
            inventoryRepository.save(inventory);
        }
    }

    @Override
    public void deleteFutureInventories(Room room) {
        LocalDate today = LocalDate.now();
        inventoryRepository.deleteByDateAfterAndRoom(today, room);
    }
}
