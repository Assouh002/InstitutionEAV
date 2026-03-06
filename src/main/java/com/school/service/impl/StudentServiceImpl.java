package com.example.school.service.impl;

import com.example.school.entity.Role;
import com.example.school.entity.Student;
import com.example.school.entity.User;
import com.example.school.exception.NotFoundException;
import com.example.school.repository.StudentRepository;
import com.example.school.repository.UserRepository;
import com.example.school.service.AccountCredentials;
import com.example.school.service.StudentService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

  private final StudentRepository repo;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private static final SecureRandom rng = new SecureRandom();

  public StudentServiceImpl(StudentRepository repo, UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.repo = repo;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override public List<Student> findAll() { return repo.findAll(); }

  @Override
  public Student findById(Long id) {
    return repo.findById(id).orElseThrow(() -> new NotFoundException("Etudiant introuvable: " + id));
  }

  @Override public Student save(Student student) { return repo.save(student); }

  @Override
  @Transactional
  public AccountCredentials createWithAccount(Student student) {
    Student saved = repo.save(student);
    String username = saved.getEmail().trim().toLowerCase();
    if (userRepository.existsByUsername(username)) {
      username = username + "." + saved.getId();
    }
    String rawPassword = generatePassword("St");
    User u = new User(username, passwordEncoder.encode(rawPassword), Role.STUDENT);
    u.setStudent(saved);
    userRepository.save(u);
    return new AccountCredentials(username, rawPassword, Role.STUDENT);
  }

  private String generatePassword(String prefix) {
    String chars = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz23456789@#$%";
    StringBuilder sb = new StringBuilder();
    sb.append(prefix).append("@");
    for (int i = 0; i < 9; i++) sb.append(chars.charAt(rng.nextInt(chars.length())));
    return sb.toString();
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    userRepository.findByStudentId(id).ifPresent(userRepository::delete);
    repo.deleteById(id);
  }
}
