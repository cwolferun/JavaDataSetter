package com.application.JavaDataSetter.util;

import com.application.JavaDataSetter.model.TaxPayer;
import com.application.JavaDataSetter.service.TaxPayerService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Scanner;

@Slf4j
@Component
public class CvsReader {
    @Autowired
    private TaxPayerService taxPayerService;
    private static CvsReader cvsReader = null;


    public static CvsReader getCvsReader() {
        if (cvsReader == null) {
            cvsReader = new CvsReader();
        }

        return cvsReader;
    }


    @SneakyThrows
    public void CsvtoDb() {

        ClassLoader classLoader = getClass().getClassLoader();

        Scanner scanner = new Scanner(new File(classLoader.getResource("New_Garda_All.csv").getFile()));
        String headerLine = scanner.nextLine();
        long start = System.currentTimeMillis();
        while (scanner.hasNext()) {
            String whole = scanner.nextLine();
            String[] parts = whole.split(",");
            TaxPayer taxPayer = TaxPayer.builder()
                    .vote(parts[6])
                    .periodName(parts[5])
                    .costCenter(parts[0])
                    .balance(Double.parseDouble(parts[4]))
                    .subheaderItem(parts[2])
                    .build();
            taxPayerService.saveTaxPayer(taxPayer);

        }
        long done = System.currentTimeMillis();
        long lapseTime = done - start;
        log.info("Time Lapsed: "+Long.toString(lapseTime));
    }

    @SneakyThrows
    String replaceCommas() {
        ClassLoader classLoader = getClass().getClassLoader();

        Scanner scanner = new Scanner(new File(classLoader.getResource("Garda_All.csv").getFile()));
        String headerLine = scanner.nextLine();

        Path newFilePath = Paths.get("src/main/resources/New_Garda_All.csv");
        Files.createFile(newFilePath);
        BufferedWriter writer = Files.newBufferedWriter(newFilePath);
        while (scanner.hasNext()) {
            String nextline = scanner.nextLine();

            if (nextline.contains("\"")) {

                String goodString = commaToSpace(nextline);
                writer.write(goodString + "\n");
                continue;
            }
            writer.write(nextline + "\n");
        }

        return headerLine;
    }

    private String commaToSpace(String line) {
        char[] chars = line.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '"') {
                for (int j = i + 1; j < chars.length; j++) {

                    if (chars[j] == ',') {
                        chars[j] = ' ';
                    }

                    if (chars[j] == '"') {
                        i = j;
                        break;

                    }

                }
            }
        }
        return new String(chars);
    }
}
