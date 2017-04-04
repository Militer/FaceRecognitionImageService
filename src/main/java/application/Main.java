package application;

import org.hibernate.cfg.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * Created by militer on 19.03.2017.
 */

@SpringBootApplication
public class Main {
    public static void main(String []args){
        SpringApplication.run(Main.class, args);
    }

//    @Bean
//    public Configuration configuration(){
//        Configuration hibernateConfiguration = new Configuration();
//        hibernateConfiguration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        hibernateConfiguration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
//        hibernateConfiguration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:32768/Bravo");
//        hibernateConfiguration.setProperty("hibernate.connection.username", "root");
//        hibernateConfiguration.setProperty("hibernate.connection.password", "manager12");
//        hibernateConfiguration.setProperty("hibernate.id.new_generator_mappings", "false");
//        return hibernateConfiguration;
//    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }
}
