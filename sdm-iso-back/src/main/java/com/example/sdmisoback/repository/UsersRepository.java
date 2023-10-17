package com.example.sdmisoback.repository;

import com.example.sdmisoback.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {
}
