package com.NotificationSite.NotificationSite.service;

import com.NotificationSite.NotificationSite.entity.Notice;
import com.NotificationSite.NotificationSite.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    public void write(Notice notice){
        noticeRepository.save(notice);
    }
}
