package com.github.itmo.ohp.backend.configuration;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;

@Configuration
public class R2dbcConfiguration {
    @Value("{administrator.username}")
    private String username;
    @Value("{administrator.password}")
    private String password;
    
    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        
//        CompositeDatabasePopulator compositeDatabasePopulator = new CompositeDatabasePopulator();
//        compositeDatabasePopulator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
//        compositeDatabasePopulator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("data.sql")));
//        compositeDatabasePopulator.addPopulators(
//                new ResourceDatabasePopulator(new ByteArrayResource(String.format(
//                        "insert into users values (\'%s\', \'%s\', \'%s\', \'%s\');",
//                        "root", username, new BCryptPasswordEncoder().encode(password), "ROLE_USER,ROLE_ADMIN"
//                ).getBytes()))
//        );
//
//        initializer.setDatabasePopulator(compositeDatabasePopulator);
        
        return initializer;
    }
}
