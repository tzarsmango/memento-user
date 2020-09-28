package com.codebenders.mementouser.repository;

import java.util.UUID;

import com.codebenders.mementouser.domain.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserData, UUID> {

    UserData findByEmail(String email);

    UserData findById(String userId);
}
