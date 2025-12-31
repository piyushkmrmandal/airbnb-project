package com.learning.project.airbnbapp.repository;

import com.learning.project.airbnbapp.entity.Inventory;
import com.learning.project.airbnbapp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    void deleteByDateAfterAndRoom(LocalDate date, Room room);
}
