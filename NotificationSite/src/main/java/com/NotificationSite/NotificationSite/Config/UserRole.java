package com.NotificationSite.NotificationSite.Config;

import lombok.Getter;

// 인증 후 사용자에게 권한 부여
@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    UserRole(String value) {
        this.value = value;
    }

    private String value;
}