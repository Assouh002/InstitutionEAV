package com.example.school.service.impl;

import com.example.school.entity.User;
import com.example.school.exception.NotFoundException;
import com.example.school.repository.UserRepository;
import com.example.school.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository repo;

  public UserServiceImpl(UserRepository repo) {
    this.repo = repo;
  }

  @Override
  public User findByUsername(String username) {
    return repo.findByUsername(username)
        .orElseThrow(() -> new NotFoundException("User not found: " + username));
  }

  @Override
  public User save(User user) {
    return repo.save(user);
  }

  @Override
  public boolean existsByUsername(String username) {
    return repo.existsByUsername(username);
  }
}
