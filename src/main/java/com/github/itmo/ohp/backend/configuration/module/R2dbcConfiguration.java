package com.github.itmo.ohp.backend.configuration.module;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Configuration
public class R2dbcConfiguration {
    @Value("${administrator.username}")
    private String username;
    @Value("${administrator.password}")
    private String password;
    
    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);

        UUID teamUUID = UUID.randomUUID();
        
        CompositeDatabasePopulator compositeDatabasePopulator = new CompositeDatabasePopulator();
        compositeDatabasePopulator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        
        // I would strongly encourage you to store queries, that do not require data from app,
        // Inside data.sql
        
        compositeDatabasePopulator.addPopulators(
                new ResourceDatabasePopulator(new ByteArrayResource(String.format(
                        "insert ignore into teams values ('%s', '%s', '%s', '%s');",
                        teamUUID, "test-team", "https://github.com/ITMO-OHP/itmo-ohp", "This is the best test team ever created."
                ).getBytes()))
        );
        
        compositeDatabasePopulator.addPopulators(
                new ResourceDatabasePopulator(new ByteArrayResource(String.format(
                        "insert ignore into users values ('%s', '%s', '%s', '%s', '%s');",
                        UUID.randomUUID(), username, SecurityConfiguration.passwordEncoder.encode(password), "ROLE_USER,ROLE_ORG,ROLE_ADMIN", teamUUID
                ).getBytes()))
        );
    
        ZonedDateTime startTime = LocalDate.of(Year.now().getValue(), Month.JANUARY, 1)
                .atStartOfDay()
                .atZone(ZoneId.systemDefault());
        String formattedStartTime = startTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    
        ZonedDateTime endTime = LocalDate.of(Year.now().plusYears(1).getValue(), Month.JANUARY, 1)
                .atStartOfDay()
                .atZone(ZoneId.systemDefault());
        String formattedEndTime = endTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        
        // Hackathon module start
        UUID hackathonId = UUID.randomUUID();
        compositeDatabasePopulator.addPopulators(
                new ResourceDatabasePopulator(new ByteArrayResource(String.format(
                                "insert ignore into hackathons values ('%s', '%s', '%s', '%s');",
                                hackathonId , formattedStartTime, formattedEndTime, false)
                    .getBytes())
                )
        );
        // Hackathon module end
        
        compositeDatabasePopulator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("data.sql")));

        initializer.setDatabasePopulator(compositeDatabasePopulator);
        
        return initializer;
    }
    
}
