package projeto.atvifma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;


@SpringBootApplication
public class AtvifmaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtvifmaApplication.class, args);
    }

    @PostConstruct
    void started() {
        // data time sem conversao de time zone
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
    }
}
