package com.NotificationSite.NotificationSite.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class NoticeList     {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String detail;

    private LocalDateTime createDate;

    @ManyToOne
    private SiteUser author;


}
