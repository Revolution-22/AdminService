package com.revolution.admin.service.api.controller;

import com.revolution.admin.service.api.port.UserService;
import com.revolution.admin.service.api.query.UserFilterQuery;
import com.revolution.admin.service.api.response.AdminUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;

    @GetMapping
    Page<AdminUserResponse> getUsers(Pageable pageable, @ModelAttribute UserFilterQuery filterQuery) {
        return userService.getUsers(pageable, filterQuery);
    }

    @PutMapping
    AdminUserResponse changeBlockStatus(@RequestParam long userId, @RequestParam boolean blockStatus) {
        return userService.changeBlockStatus(userId, blockStatus);
    }
}
