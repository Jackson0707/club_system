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

	List<Student> findByNameContainingAndStudentIdContainingAndSemesterContainingAndGradeContainingAndStatus(
			String name, int studentId, String semester, String grade, String status);
	List<Student> findByName(String name);
	List<Student> findByStudentId(int studentId);
	List<Student> findBySemester(String semester);
	List<Student> findByGrade(String grade);
	List<Student> findByStatus(String sttus);

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
	
	// �]�� Entity student �����h��(2�өΥH�W)�ݩʦ��[�W@Id:�Y�_�X(�h)�D��A
	// �ҥH�z�L studentId ���O�N�o���ݩʶ����޲z�A�]���b Dao ���A�~�Ӫ� JpaRepository<T,ID>
	// �� ID �쥻�O���[ @Id ���ݩʤ���ƫ��A�A���]�ƦX(�h)�D��A�N�i�H�� studentId �ӥN���i���Ҧ�PK
	// ����ƫ��A
}
