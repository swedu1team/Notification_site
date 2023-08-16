package com.NotificationSite.NotificationSite.repository;

import com.NotificationSite.NotificationSite.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {
}
