package com.example.club_system.repository;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.club_system.entity.Club;
import com.example.club_system.entity.TeacherDatabase;


@Repository
public interface ClubDao extends JpaRepository<Club, Integer>{

	@Transactional
	@Modifying
	@Query(value= "delete from club where club_id = ?1", nativeQuery = true)
	public int delete(Integer club_id);
	
	@Query(value="select * from club where club_id = ?1", nativeQuery = true)
	public List<Club> selectAll(  );
	
	@Query(value="select * from club where name like concat('%',?1,'%')", nativeQuery = true)
	public List<Club> selectByName(String name );
	
//	@Query(value="select * from club where teacher_id like concat('%',?1,'%')", nativeQuery = true)
//	public List<Club> selectByTeacherId(String teacger_id );
	
//	@Query(value="select * from club where semester like concat('%',?1,'%')", nativeQuery = true)
//	public List<Club> selectBySemester(String semester );
	
	
//	List<TeacherDatabase> findByNameContainingNameAndTeacherIdAndSemester(String name, String semester, int TeacherId);
	
}
