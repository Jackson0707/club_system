package com.example.club_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.club_system.entity.Club;

@Repository
public interface ClubDao extends JpaRepository<Club, Integer> {

	List<Club> findByNameContainingAndClubIdAndSemesterAndTeacherId(int clubId, String name, String semester, int teacherId);
	
	List<Club> findByNameContainingAndClubIdAndSemester(int clubId, String name, String semester);

	List<Club> findByNameContainingAndClubIdAndTeacherId(int clubId, String name, int teacherId);

	List<Club> findByNameContainingAndSemesterAndTeacherId( String name, String semester, int teacherId);
	
	List<Club> findByNameContainingAndSemester( String name, String semester);

	List<Club> findByClubIdAndTeacherId(int sclubId, int teacherId);
	
	List<Club> findByClubId(int sclubId);

//	@Transactional
//	@Modifying
//	@Query(value = "delete from club where club_id = ?1", nativeQuery = true)
//	public int delete(Integer club_id);
//
//	@Query(value = "select * from club where club_id = ?1", nativeQuery = true)
//	public List<Club> selectAll();
//
//	@Query(value = "select * from club where name like concat('%',?1,'%')", nativeQuery = true)
//	public List<Club> selectByName(String name);
//
//	@Query(value = "select * from club where teacher_id like concat('%',?1,'%')", nativeQuery = true)
//	public List<Club> selectByTeacherId(String teacger_id);
//
//	@Query(value = "select * from club where semester like concat('%',?1,'%')", nativeQuery = true)
//	public List<Club> selectBySemester(String semester);

}
