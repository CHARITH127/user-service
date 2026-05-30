package com.user.user_service.repository;

import com.user.user_service.entity.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermission, UUID> {
}
