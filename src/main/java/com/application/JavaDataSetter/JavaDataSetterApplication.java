package com.application.JavaDataSetter;

import com.application.JavaDataSetter.util.CvsReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/*
dat pulled from https://opendata.socrata.com/dataset/Garda-All/dxx6-qun4

 */
@SpringBootApplication
public class JavaDataSetterApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaDataSetterApplication.class, args);
    }


    @Component
public class ApplicationStartup
implements ApplicationListener<ApplicationReadyEvent> {

        @Autowired
        CvsReader cvsReader;

        @Override
        public void onApplicationEvent(final ApplicationReadyEvent event) {

            cvsReader.CsvtoDb();
        }
    }
}
