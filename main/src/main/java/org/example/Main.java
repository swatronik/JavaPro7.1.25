package org.example;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.sql.init.SqlDataSourceScriptDatabaseInitializer;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserService service) {
        return (args) -> {
            Long user1 = service.insertUser("petr");
            Long user2 = service.insertUser("oleg");
            Long user3 = service.insertUser("vadim");

            service.getAllUsers().forEach(System.out::println);
            System.out.println();

            service.updateUser(user2, "ivan");
            User user = service.getUser(user2);
            System.out.println(user);
            System.out.println();

            service.deleteUser(user1);
            service.deleteUser(user2);
            service.deleteUser(user3);
            service.getAllUsers().forEach(System.out::println);
        };
    }

    @Bean
    SqlDataSourceScriptDatabaseInitializer dataSourceScriptDatabaseInitializer(DataSource dataSource,
                                                                               SqlInitializationProperties properties, UserRepository repository) {
        return new SqlDataSourceScriptDatabaseInitializer(dataSource, properties) {
            @Override
            public boolean initializeDatabase() {
                if (repository.count() == 0L) {
                    return super.initializeDatabase();
                }
                return false;
            }
        };
    }
}