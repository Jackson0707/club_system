package com.example.club_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.club_system.entity.Club;
import com.example.club_system.entity.Student;

@Repository
public interface ClubDao extends JpaRepository<Club, Integer> {
	
	
	@Query(value = "delete from club where club_id = ?1", nativeQuery = true)
	public int delete(Integer club_id);
	
//	List<Club> findBySemester(String semester);
//	
//	List<Club> findByName(String name);
//
//	List<Club> findByNameContainingAndSemester( String name, String semester);
//	
//	List<Club> findByNameContainingOrSemesterContainingOrTeacherIdContainingOrClubId( String name, String semester, int teacherId, int clubId);
//	
//	List<Club> findByNameContainingAndSemesterContainingAndTeacherId( String name, String semester, int teacherId);
//
//	List<Club> findByNameContainingAndSemesterContainingAndClubId( String name, String semester, int clubId);
//
//	List<Club> findByClubIdContainingAndTeacherId(int sclubId, int teacherId);
//	
//	List<Club> findAllByClubId(int sclubId);
//	
//	List<Club> findAllByTeacherId(int teacherId);

	@Query("SELECT c FROM Club c WHERE " +
		       "(:name IS NULL OR c.name LIKE CONCAT('%', :name, '%')) AND " +
		       "(:semester IS NULL OR c.semester LIKE CONCAT('%', :semester, '%')) AND " +
		       "(:teacherId IS NULL OR c.teacherId LIKE CONCAT('%', :teacherId, '%')) AND " +
		       "(:clubId IS NULL OR c.clubId = :clubId)")
		List<Club> findByNameAndSemesterAndTeacherIdAndClubId(
		        @Param("name") String name,
		        @Param("semester") String semester,
		        @Param("teacherId") Integer teacherId,
		        @Param("clubId") Integer clubId);
	




}
