package com.NotificationSite.NotificationSite.service;

import com.NotificationSite.NotificationSite.entity.Notice;
import com.NotificationSite.NotificationSite.entity.NoticeList;
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
    public void write(Notice notice){
        noticeRepository.save(notice);
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
}
