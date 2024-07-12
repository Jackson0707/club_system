package com.example.club_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.club_system.entity.Student;
import com.example.club_system.entity.StudentId;

@Repository
public interface StudentDao extends JpaRepository<Student, StudentId> {

	Optional<Student> findBystudentId(int studentId);

	// �]�� Entity student �����h��(2�өΥH�W)�ݩʦ��[�W@Id:�Y�_�X(�h)�D��A
	// �ҥH�z�L studentId ���O�N�o���ݩʶ����޲z�A�]���b Dao ���A�~�Ӫ� JpaRepository<T,ID>
	// �� ID �쥻�O���[ @Id ���ݩʤ���ƫ��A�A���]�ƦX(�h)�D��A�N�i�H�� studentId �ӥN���i���Ҧ�PK
	// ����ƫ��A
}
