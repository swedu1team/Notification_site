package com.NotificationSite.NotificationSite.repository;

import com.NotificationSite.NotificationSite.entity.NoticeList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeListRepository extends JpaRepository<NoticeList,Integer> {
    Page<NoticeList> findAll(Pageable pageable);
}
