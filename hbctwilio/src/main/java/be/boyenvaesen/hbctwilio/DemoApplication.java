package be.boyenvaesen.hbctwilio;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@PropertySource(value = "classpath:apikeys.properties")
@ComponentScan
@EnableWebMvc
@Configuration
@EnableScheduling
public class DemoApplication {


    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args

        );
    }



}


