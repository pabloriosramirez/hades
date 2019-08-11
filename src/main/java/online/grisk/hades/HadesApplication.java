package online.grisk.hades;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("integration.cfg.xml")
public class HadesApplication {
    public static void main(String[] args) {
        SpringApplication.run(HadesApplication.class, args);
    }

}
