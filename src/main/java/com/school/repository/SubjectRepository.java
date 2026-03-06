package com.example.school.repository;

import com.example.school.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
  List<Subject> findByProfessorId(Long professorId);
}
