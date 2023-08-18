package com.NotificationSite.NotificationSite.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer NOTICE_ID; //공지 번호

    private String CONTENT; // 공지 내용

    // private String USER_ID; // 작성자 id

    private String MEET_DAY; //공지 날짜

    private String MEET_PLACE; // 공지 장소

    private String MEET_SUBJECT; //공지 제목

   @ManyToOne
    private SiteUser author; // 유저 정보
}