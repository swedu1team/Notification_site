package com.NotificationSite.NotificationSite.controller;

import com.NotificationSite.NotificationSite.entity.Notice;
import com.NotificationSite.NotificationSite.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/notice")
@Controller
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @GetMapping
    public String noticeList(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Notice> paging = this.noticeService.getList(page);
        model.addAttribute("paging", paging);
        return "notice_list";
    }


    //공지사항 작성 html과 연결
    @GetMapping("/noticewrite")
    public String noticeWriteForm() {

        return "noticewrite";
    }

    //공지사항 작성 기능
    @PostMapping("/noticewritepro")
    public String noticeWritePro(Notice notice){
        noticeService.write(notice);
        return "notice_list";
    }

    //공지사항 상세
    @GetMapping("/noticeview")
    public String noticeView(Model model, Integer NOTICE_ID){
        model.addAttribute("Notice",noticeService.noticeView(NOTICE_ID));
        return "noticeview";
    }

    //공지사항 수정
    @GetMapping("/noticemodify/{NOTICE_ID}")
    public String noticeModify(Model model, @PathVariable("NOTICE_ID") Integer NOTICE_ID){
        model.addAttribute("Notice",noticeService.noticeView(NOTICE_ID));
        return "noticemodify";
    }

    @PostMapping("/noticeupdate/{NOTICE_ID}")
    public String noticeUpdate(@PathVariable("NOTICE_ID") Integer NOTICE_ID, Notice notice){
        //수정된 내용 업데이트
        Notice temp = noticeService.noticeView(NOTICE_ID);
        temp.setCONTENT(notice.getCONTENT());
        temp.setMEET_DAY(notice.getMEET_DAY());
        temp.setMEET_PLACE(notice.getMEET_PLACE());
        temp.setMEET_SUBJECT(notice.getMEET_SUBJECT());

        noticeService.write(temp);

        return "notice_list";
    }
}
