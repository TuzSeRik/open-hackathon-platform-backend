package com.github.itmo.ohp.backend.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component @RequiredArgsConstructor
public class DatabaseAdministratorCreator // implements ApplicationListener<ContextRefreshedEvent>
{
//    private final AuthorizationService authorizationService;
//    private boolean alreadySet;
//    @Value("{administrator.username}")
//    private String username;
//    @Value("{administrator.password}")
//    private String password;
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        if (!alreadySet) {
//            authorizationService.addUser(new User(
//                    UUID.randomUUID(),
//                    username,
//                    new BCryptPasswordEncoder().encode(password),
//                    "ROLE_USER;ROLE_ADMIN"
//            ));
//
//            alreadySet = true;
//        }
//    }
}
