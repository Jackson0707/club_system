package com.example.club_system.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.club_system.entity.Club;

@Repository
public interface ClubDao extends JpaRepository<Club, Integer>{

	public List<Club> findByNameContaining(String name);
	
}
