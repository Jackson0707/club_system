package com.example.club_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.club_system.entity.TeacherDatabase;

@Repository
public interface TeacherDatabaseDao extends JpaRepository<TeacherDatabase, Integer> {
	
//	List<TeacherDatabase> findByNameContainingAndStatusContainingAndClubIdAndTeacherId(String name,
//			String status, Integer clubId, Integer teacherId);
//	List<TeacherDatabase> findByTeacherId(Integer teacherId);
//	List<TeacherDatabase> findByClubId(Integer clubId);
//	List<TeacherDatabase> findByName(String name);
//	List<TeacherDatabase> findByStatus(String status);
	
	@Query("SELECT rd FROM TeacherDatabase rd WHERE" //
			+ "(:name IS NULL OR rd.name LIKE %:name%) AND"//
			+ "(:status IS NULL OR rd.status LIKE %:status%) AND" //
			+ "(:clubId IS NULL OR rd.clubId = :clubId) AND"
			+ "(:teacherId IS NULL OR rd.teacherId = :teacherId)")
	List<TeacherDatabase> findByNameAndStatusAndClubIdAndTeacherId(@Param("name") String name,
			@Param("status") String status,
			@Param("clubId") Integer clubId,
			@Param("teacherId") Integer teacherId);
}
