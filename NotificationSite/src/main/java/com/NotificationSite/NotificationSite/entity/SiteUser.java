package com.NotificationSite.NotificationSite.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Getter
@Setter
@Entity
public class SiteUser {

    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String userId;

    private String userPassword;

    @Column(unique=true)
    private String userEmail;


}