package com.NotificationSite.NotificationSite;

import com.NotificationSite.NotificationSite.controller.NoticeController;
import com.NotificationSite.NotificationSite.service.NoticeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.containsString;

@WebMvcTest(NoticeController.class) // NoticeController 테스트
@AutoConfigureMockMvc
public class NoticeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoticeService noticeService; // NoticeService가 MockBean으로 대체됨

    @Test
    public void testCreateNoticeAndCheckListPage() throws Exception {
        // 공지사항 작성 요청을 보내고 결과 확인
        ResultActions result = mockMvc.perform(post("/notice/noticewrite")
                .param("CONTENT", "새로운 공지사항 내용")
                .param("USER_ID", "user1")
                .param("MEET_DAY", "2023-08-18")
                .param("MEET_PLACE", "회의실")
                .param("MEET_SUBJECT", "새로운 공지 제목"));

        // 작성 후 리다이렉트되는 페이지 확인
        result.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/notice/list"));

        // 목록 페이지 접속
        mockMvc.perform(get("/notice/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("paging"))
                .andExpect(view().name("notice_list"))
                .andExpect(content().string(containsString("새로운 공지 제목"))); // 작성한 내용 확인
    }
}