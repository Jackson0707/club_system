package com.example.club_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.club_system.entity.Club;

@Repository
public interface ClubDao extends JpaRepository<Club, Integer> {
	
	
	@Query(value = "delete from club where club_id = ?1", nativeQuery = true)
	public int delete(Integer club_id);
	
	
	
//	@Query(value = "select * from club where " +
//	        "name like concat('%', :name, '%') or " +
//	        "teacher_id = :teacherId or " +
//	        "semester like concat('%', :semester, '%')", 
//	    nativeQuery = true)
//	public List<Club> searchSql(@Param("name") String name, 
//	                            @Param("teacherId") int teacherId, 
//	                            @Param("semester") String semester);
//	
//	
//	@Query(value = "select * from club ", nativeQuery = true)
//	public List<Club> selectall();
	
	
	List<Club> findBySemester(String semester);
	
	List<Club> findByName(String name);

	List<Club> findByNameContainingAndSemester( String name, String semester);
	
	List<Club> findByNameContainingOrSemesterContainingOrTeacherIdContainingOrClubId( String name, String semester, int teacherId, int clubId);
	
	List<Club> findByNameContainingAndSemesterContainingAndTeacherId( String name, String semester, int teacherId);

	List<Club> findByNameContainingAndSemesterContainingAndClubId( String name, String semester, int clubId);

	List<Club> findByClubIdContainingAndTeacherId(int sclubId, int teacherId);
	
	List<Club> findAllByClubId(int sclubId);
	
	List<Club> findAllByTeacherId(int teacherId);
	
//	    @Query(value = "SELECT * FROM club WHERE " +
//	            "(:name = '' OR name LIKE CONCAT('%', :name, '%')) AND " +
//	            "(:semester = '' OR semester LIKE CONCAT('%', :semester, '%')) AND " +
//	            "(:clubId = '' OR CAST(club_id AS CHAR) LIKE CONCAT('%', :clubId, '%')) AND " +
//	            "(:teacherId = '' OR CAST(teacher_id AS CHAR) LIKE CONCAT('%', :teacherId, '%'))",
//	            nativeQuery = true)
//	    List<Club> searchClubs(@Param("name") String name,
//	                           @Param("semester") String semester,
//	                           @Param("clubId") String clubId,
//	                           @Param("teacherId") String teacherId);


	




}
