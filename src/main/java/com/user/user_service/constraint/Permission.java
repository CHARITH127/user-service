package com.user.user_service.constraint;

import lombok.Getter;

@Getter
public enum Permission {
    USER_READ("Read User"),
    USER_REMOVE("Remove User"),
    REPORT_EXPORT("Report Export"),;

    private final String displayName;

    Permission(String displayName) {
        this.displayName = displayName;
    }
}
