package com.example.club_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.club_system.entity.TeacherDatabase;

@Repository
public interface TeacherDatabaseDao extends JpaRepository<TeacherDatabase, Integer> {
	List<TeacherDatabase> findByNameContainingAndStatusContainingAndClubIdContainingAndTeacherId(String name,
			String status, int clubId, int teacherId);
	List<TeacherDatabase> findByTeacherId(int teacherId);
	List<TeacherDatabase> findByClubId(int clubId);
	List<TeacherDatabase> findByName(String name);
	List<TeacherDatabase> findByStatus(String status);
}
