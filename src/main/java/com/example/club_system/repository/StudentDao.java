package com.example.club_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.club_system.entity.Student;

@Repository
public interface StudentDao extends JpaRepository<Student, Integer> {

	List<Student> findByClubId(Integer clubId);

	Optional<Student> findBystudentId(Integer studentId);

	boolean existsBySemester(String semester);

	List<Student> findByNameContainingAndStudentIdContainingAndSemesterContainingAndGradeContainingAndStatus(
			String name, Integer studentId, String semester, String grade, String status);

	Optional<Student> findFirstBystudentIdOrderByUpdateTimeDesc(Integer studentId);

	
	
	@Query("SELECT s FROM Student s WHERE " +
		       "(:name IS NULL OR s.name LIKE CONCAT('%', :name, '%')) AND " +
		       "(:status IS NULL OR s.status LIKE CONCAT('%', :status, '%')) AND " +
		       "(:grade IS NULL OR s.grade LIKE CONCAT('%', :grade, '%')) AND " +
		       "(:semester IS NULL OR s.semester LIKE CONCAT('%', :semester, '%')) AND " +
		       "(:studentId IS NULL OR s.studentId = :studentId)")
		List<Student> findByNameAndStatusAndStudentIdAndSemesterAndGrade(
		        @Param("name") String name,
		        @Param("status") String status,
		        @Param("studentId") Integer studentId,
		        @Param("semester") String semester,
		        @Param("grade") String grade);


	
	
}
