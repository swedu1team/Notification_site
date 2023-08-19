package com.NotificationSite.NotificationSite.service;

import com.NotificationSite.NotificationSite.entity.Notice;
import com.NotificationSite.NotificationSite.entity.NoticeList;
import com.NotificationSite.NotificationSite.entity.SiteUser;
import com.NotificationSite.NotificationSite.repository.NoticeListRepository;
import com.NotificationSite.NotificationSite.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private NoticeListRepository noticeListRepository;

    //공지사항 작성
    public void write(Notice notice, SiteUser author){
        Notice notice1 = new Notice();
        notice1.setMeetSubject(notice.getMeetSubject());
        notice1.setMeetDay(notice.getMeetDay());
        notice1.setMeetPlace(notice.getMeetPlace());
        notice1.setContent(notice.getContent());
        notice1.setUsername(author);
        this.noticeRepository.save(notice1);
    }

    //공지사항 수정
    public void modiwrite(Notice notice){
        notice.setMeetSubject(notice.getMeetSubject());
        notice.setMeetDay(notice.getMeetDay());
        notice.setMeetPlace(notice.getMeetPlace());
        notice.setContent(notice.getContent());
        this.noticeRepository.save(notice);
    }

    // 공지사항 세부
    public Notice noticeView(Integer id){
        return noticeRepository.findById(id).get();
    }


    public Page<NoticeList> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.noticeListRepository.findAll(pageable);
    }
}
