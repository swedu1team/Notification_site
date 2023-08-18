package com.NotificationSite.NotificationSite.service;

import com.NotificationSite.NotificationSite.Config.DataNotFoundException;
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
import java.util.Optional;

@Service
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private NoticeListRepository noticeListRepository;

    //공지사항 작성
    public Notice write(String title, String day, String address, String content, SiteUser user){
        Notice n =new Notice();
        n.setMEET_SUBJECT(title);
        n.setMEET_DAY(day);
        n.setMEET_PLACE(address);
        n.setCONTENT(content);
        n.setAuthor(user);
        return noticeRepository.save(n);
    }

    //공지사항 세부
    public Notice noticeView(Integer NOTICE_ID){
        return noticeRepository.findById(NOTICE_ID).get();
    }

    public Page<NoticeList> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.noticeListRepository.findAll(pageable);
    }

    // 공지사항 수정
    public void modify(Notice notice, String subject, String day,String address, String content){
        Notice n = new Notice();
        n.setMEET_SUBJECT(subject);
        n.setMEET_DAY(day);
        n.setMEET_PLACE(address);
        n.setCONTENT(content);
        this.noticeRepository.save(n);
    }

    //수정시 공지사항 Id 존재 유무 확인
    public Notice getNotice(Integer id) {
        Optional<Notice> notice = this.noticeRepository.findById(id);
        if (notice.isPresent()) {
            return notice.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }
}
