package com.NotificationSite.NotificationSite.controller;

import com.NotificationSite.NotificationSite.Form.NoticeForm;
import com.NotificationSite.NotificationSite.entity.Notice;
import com.NotificationSite.NotificationSite.entity.NoticeList;
import com.NotificationSite.NotificationSite.entity.SiteUser;
import com.NotificationSite.NotificationSite.service.NoticeService;
import com.NotificationSite.NotificationSite.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/notice")
@Controller
public class NoticeController {
    @Autowired
    private final NoticeService noticeService;

    @Autowired
    private final UserService userService;


    @GetMapping("/list")
    public String noticeList(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<NoticeList> paging = this.noticeService.getList(page);
        model.addAttribute("paging", paging);
        return "notice_list";
    }


    //공지사항 작성 html과 연결

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/noticewrite")
    public String noticeWriteForm() {

        return "noticewrite";
    }

    //공지사항 작성 기능
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/noticewritepro")
    public String noticeWritePro(@Valid NoticeForm noticeForm, BindingResult bindingResult, Principal principal){
        if (bindingResult.hasErrors()) {
            return "noticewrtie";
        }
        SiteUser siteUser = this.userService.getUser(principal.getName());
        Notice noice = this.noticeService.write(noticeForm.getCONTENT(),noticeForm.getMEET_DAY(), noticeForm.getMEET_PLACE(), noticeForm.getMEET_SUBJECT(),siteUser);
        return "redirect:/notice/list";
    }

    //공지사항 상세
    @GetMapping("/noticeview")
    public String noticeView(Model model, Integer NOTICE_ID){
        model.addAttribute("Notice",noticeService.noticeView(NOTICE_ID));
        return "noticeview";
    }

    //공지사항 수정
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/noticemodify/{NOTICE_ID}")
    public String noticeModify(Model model, @PathVariable("NOTICE_ID") Integer NOTICE_ID, Principal principal){
        model.addAttribute("Notice",noticeService.noticeView(NOTICE_ID));
        return "noticemodify";
    }

    //수정된 내용 업데이트
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/noticeupdate/{NOTICE_ID}")
    public String noticeUpdate(@Valid NoticeForm noticeForm,
                               BindingResult bindingResult,
                               Principal principal,
                               @PathVariable("NOTICE_ID") Integer NOTICE_ID)
    {
        if (bindingResult.hasErrors()) {
            return "noticewrtie";
        }
        //수정된 내용 업데이트
        Notice notice = this.noticeService.getNotice(NOTICE_ID);
        if (!notice.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        this.noticeService.modify(notice, noticeForm.getMEET_SUBJECT(), noticeForm.getMEET_DAY(), noticeForm.getMEET_PLACE(),noticeForm.getCONTENT());

        return String.format("redirect:/notice/list/%s", NOTICE_ID);
    }
}
