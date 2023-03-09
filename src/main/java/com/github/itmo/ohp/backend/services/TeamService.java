package com.github.itmo.ohp.backend.services;

import com.github.itmo.ohp.backend.model.Team;
import com.github.itmo.ohp.backend.repositories.UserRepository;
import com.github.itmo.ohp.backend.responses.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService{
    private final UserRepository userRepository;
    private final DatabaseClient client;

    private static final String SELECT_QUERY = """
            SELECT teams.id as teamId, teams.name as teamName, teams.github as teamGithub, teams.info as teamInfo,
            users.id as userId, users.username as userName 
            FROM teams
            LEFT JOIN users ON users.team_id = teams.id
            """;

    public Flux<Team> findAll() {
        return client.sql(SELECT_QUERY)
                .fetch()
                .all()
                .bufferUntilChanged(result -> result.get("teamId"))
                .map(list -> {
                        Team.TeamBuilder teamBuilder = Team.builder();
                        teamBuilder.id(UUID.fromString(String.valueOf(list.get(0).get("teamId"))));
                        teamBuilder.name(String.valueOf(list.get(0).get("teamName")));
                        teamBuilder.github(String.valueOf(list.get(0).get("teamGithub")));
                        teamBuilder.info(String.valueOf(list.get(0).get("teamInfo")));
                        teamBuilder.users(
                                list.stream().map(userList -> UserResponse.builder()
                                        .id(UUID.fromString(String.valueOf(userList.get("userId"))))
                                        .username(String.valueOf(userList.get("userName")))
                                        .build())
                                        .collect(Collectors.toSet())
                        );
                        return teamBuilder.build();
                });
    }

    public Mono<Team> findById(UUID id) {
        return client.sql(String.format("%s WHERE teams.id = :id", SELECT_QUERY))
                .bind("id", id)
                .fetch()
                .all()
                .bufferUntilChanged(result -> result.get("teamId"))
                .map(list -> {
                    Team.TeamBuilder teamBuilder = Team.builder();
                    teamBuilder.id(UUID.fromString(String.valueOf(list.get(0).get("teamId"))));
                    teamBuilder.name(String.valueOf(list.get(0).get("teamName")));
                    teamBuilder.github(String.valueOf(list.get(0).get("teamGithub")));
                    teamBuilder.info(String.valueOf(list.get(0).get("teamInfo")));
                    teamBuilder.users(
                            list.stream().map(userList -> UserResponse.builder()
                                            .id(UUID.fromString(String.valueOf(userList.get("userId"))))
                                            .username(String.valueOf(userList.get("userName")))
                                            .build())
                                    .collect(Collectors.toSet())
                    );
                    return teamBuilder.build();
                })
                .singleOrEmpty();
    }
}
