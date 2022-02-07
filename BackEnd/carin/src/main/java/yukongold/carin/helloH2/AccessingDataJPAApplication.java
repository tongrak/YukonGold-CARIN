package yukongold.carin.helloH2;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingDataJPAApplication {

    private static final Logger log = LoggerFactory.getLogger(AccessingDataJPAApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataJPAApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(SimpleObjRepository repository){
        return (args) -> {
            repository.save(new SimpleObj("A1", "Pein"));
            repository.save(new SimpleObj("B69", "Deez"));
            repository.save(new SimpleObj("A45", "Ohhhhhhhh"));
            repository.save(new SimpleObj("Z9", "What?"));

            log.info("SimpleObj found with findAll(): ");
            log.info("--------------------------------");
            for (SimpleObj s :
                    repository.findAll()) {
                log.info(s.toString());
            }
            log.info("");

            SimpleObj sim1 = repository.findById(1L);
            log.info("SimpleObj found with findById(1L)");
            log.info("---------------------------------");
            log.info(sim1.toString());
            log.info("");

            log.info("Customer found with findByName(\"B69\")");
            log.info("---------------------------------------");
            repository.findByName("B69").forEach(b69 -> {log.info(b69.toString());});
            log.info("");

        };
    }
}
