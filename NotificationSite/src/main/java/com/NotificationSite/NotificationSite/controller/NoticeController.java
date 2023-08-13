package com.NotificationSite.NotificationSite.controller;

import com.NotificationSite.NotificationSite.entity.Notice;
import com.NotificationSite.NotificationSite.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.management.loading.PrivateClassLoader;

@Controller
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    //공지사항 작성 html과 연결
    @GetMapping("notice/noticewrite")
    public String noticeWriteForm(){
        return "noticewrite";
    }

    //공지사항 작성 기능
    @PostMapping("notice/noticewritepro")
    public String noticeWritePro(Notice notice){
        noticeService.write(notice);
        return "noticelist";
    }
}
