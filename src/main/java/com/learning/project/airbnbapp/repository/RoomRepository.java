package com.learning.project.airbnbapp.repository;

import com.learning.project.airbnbapp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Long id(Long id);
}
