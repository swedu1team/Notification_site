package com.NotificationSite.NotificationSite.repository;

import com.NotificationSite.NotificationSite.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
}