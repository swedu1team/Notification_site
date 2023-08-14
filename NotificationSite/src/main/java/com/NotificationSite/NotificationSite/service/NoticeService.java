package com.NotificationSite.NotificationSite.service;

import com.NotificationSite.NotificationSite.entity.Notice;
import com.NotificationSite.NotificationSite.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    //공지사항 작성
    public void write(Notice notice){
        noticeRepository.save(notice);
    }

    //공지사항 세부
    public Notice noticeView(Integer NOTICE_ID){
        return noticeRepository.findById(NOTICE_ID).get();
    }
}
