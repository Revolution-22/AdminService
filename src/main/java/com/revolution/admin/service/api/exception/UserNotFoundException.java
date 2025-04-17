package com.revolution.admin.service.api.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(final long userId) {
        super("Can not found user with id " + userId);
    }
}
