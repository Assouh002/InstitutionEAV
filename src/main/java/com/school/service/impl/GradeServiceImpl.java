package com.example.school.service.impl;

import com.example.school.entity.Grade;
import com.example.school.exception.NotFoundException;
import com.example.school.repository.GradeRepository;
import com.example.school.service.GradeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

  private final GradeRepository repo;

  public GradeServiceImpl(GradeRepository repo) {
    this.repo = repo;
  }

  @Override public List<Grade> findAll() { return repo.findAll(); }

  @Override
  public Grade findById(Long id) {
    return repo.findById(id).orElseThrow(() -> new NotFoundException("Note introuvable: " + id));
  }

  @Override public Grade save(Grade grade) { return repo.save(grade); }
  @Override public void deleteById(Long id) { repo.deleteById(id); }
  @Override public List<Grade> findByStudentId(Long studentId) { return repo.findByStudentId(studentId); }
  @Override public List<Grade> findByStudentIdSorted(Long studentId) { return repo.findByStudentIdOrderBySubjectNameAsc(studentId); }
  @Override public void deleteByStudentId(Long studentId) { repo.deleteByStudentId(studentId); }
}
