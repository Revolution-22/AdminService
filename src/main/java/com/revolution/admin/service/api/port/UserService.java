package com.revolution.admin.service.api.port;

import com.revolution.admin.service.api.query.UserFilterQuery;
import com.revolution.admin.service.api.response.AdminUserResponse;
import com.revolution.common.event.RegisterEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    AdminUserResponse handleUserRegister(RegisterEvent event);

    AdminUserResponse getUserById(long userId);

    Page<AdminUserResponse> getUsers(Pageable pageable, UserFilterQuery filterQuery);

    AdminUserResponse changeBlockStatus(long userId, boolean blockStatus);
}
