package ru.sberdata;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = {"ru.sberdata"})
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        this.onStartup();
    }

    public void onStartup() {
        System.out.println("\n\n\t+=====================================+");
        System.out.println("\t|   STARTING SBERDATA WEB APPLICATION   |");
        System.out.println("\t+=====================================+\n\n");
    }
}
