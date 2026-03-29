package com.user.user_service.graphql;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.user.user_service.dtos.RegisterInput;
import com.user.user_service.entity.User;
import com.user.user_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@DgsComponent
@RequiredArgsConstructor
public class UserDataFetcher {

    @Autowired
    private AuthService authService;

    @DgsQuery
    public User userById(@InputArgument String id) {
        return authService.findByUserId(UUID.fromString(id));
    }

    @DgsQuery
    public User userByUsername(@InputArgument String username) {
        return authService.findByUserName(username);
    }

    @DgsMutation
    public User registerUser(@InputArgument("input") RegisterInput input) {
        return authService.register(
                input.userName(),
                input.password(),
                input.role()
        );
    }
}
