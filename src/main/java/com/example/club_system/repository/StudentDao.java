package com.example.club_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.club_system.entity.Student;
import com.example.club_system.entity.StudentId;
import com.example.club_system.vo.BasicRes;

@Repository
public interface StudentDao extends JpaRepository<Student, StudentId> {

	Optional<Student> findBystudentId(int studentId);

	boolean existsBySemester(String semester);

	List<Student> findByNameContainingAndStatusContainingAndSemesterContainingAndGrade(String name,
			String status, String semester, String grade);

	List<Student> findByNameContainingAndStatusContainingAndSemesterContainingAndGradeContainingAndStudentIdAndClubId(
			String name, String status, String semester, String grade, int studentId, int clubId);

	List<Student> findByNameContainingAndStatusContainingAndSemesterContainingAndGradeContainingAndStudentId(
			String name, String status, String semester, String grade, int studentId);

	List<Student> findByNameContainingAndStatusContainingAndSemesterContainingAndGradeContainingAndClubId(
			String name, String status, String semester, String grade, int clubId);

	Optional<Student> findFirstBystudentIdOrderByUpdateTimeDesc(int studentId);

	List<Student> findByStudentIdAndClubId(int studentId, int clubId);
	
	List<Student> findByClubId(int clubId);
	
//	@Query(value="select * from student where choice_list like concat('?1,%')", nativeQuery = true)
//	List<Student> findByChoiceOne(int clubId);
//	
//	@Query(value="select * from student where choice_list like concat('%,?1,%')", nativeQuery = true)
//	List<Student> findByChoiceTwo(int clubId);
//	
//	@Query(value="select * from student where choice_list like concat('%,?1')", nativeQuery = true)
//	List<Student> findByChoiceThree(int clubId);
	
	// 因為 Entity student 中有多個(2個或以上)屬性有加上@Id:即復合(多)主鍵，
	// 所以透過 studentId 類別將這些屬性集中管理，因此在 Dao 中，繼承的 JpaRepository<T,ID>
	// 的 ID 原本是有加 @Id 的屬性之資料型態，但因複合(多)主鍵，就可以用 studentId 來代表整張表的所有PK
	// 的資料型態
}
