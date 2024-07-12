package com.example.club_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.club_system.entity.Student;
import com.example.club_system.entity.StudentId;

@Repository
public interface StudentDao extends JpaRepository<Student, StudentId> {

	Optional<Student> findBystudentId(int studentId);

	// 因為 Entity student 中有多個(2個或以上)屬性有加上@Id:即復合(多)主鍵，
	// 所以透過 studentId 類別將這些屬性集中管理，因此在 Dao 中，繼承的 JpaRepository<T,ID>
	// 的 ID 原本是有加 @Id 的屬性之資料型態，但因複合(多)主鍵，就可以用 studentId 來代表整張表的所有PK
	// 的資料型態
}
