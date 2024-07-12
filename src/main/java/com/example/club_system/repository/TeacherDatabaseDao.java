package com.example.club_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.club_system.entity.TeacherDatabase;

@Repository
public interface TeacherDatabaseDao extends JpaRepository<TeacherDatabase, Integer> {
	List<TeacherDatabase> findByNameContainingAndStatusContainingAndClubIdOrderByTeacherIdAsc(String name,
			String status, int clubId);
}
