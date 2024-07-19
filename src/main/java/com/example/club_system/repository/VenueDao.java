package com.example.club_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.club_system.entity.Venue;

@Repository
public interface VenueDao extends JpaRepository<Venue, String> {

	List<Venue> findByIdContaining(String id);
}
