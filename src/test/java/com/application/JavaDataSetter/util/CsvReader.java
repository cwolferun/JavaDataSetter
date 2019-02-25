package com.application.JavaDataSetter.util;

import com.application.JavaDataSetter.model.TaxPayer;
import com.application.JavaDataSetter.service.TaxPayerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest()
public class CsvReader {

   private CvsReader cvsReader = CvsReader.getCvsReader();

    @Autowired
    TaxPayerService taxPayerService;

    @Test
    public void replaceCommas(){
        String line = cvsReader.replaceCommas();

        String expected = "101,1,,4A,27-29 S WARREN ST,\"KHOSLA  RENUKA\",30 CHICOPEE DR #F,PRINCETON NJ,08540,HIST,,35.7X82,1SB 1761SF,,59000.00,52000.00,111000.00";

        assertEquals(expected,line);
    }

    @Test
    public void insertOne(){
        TaxPayer taxPayer = TaxPayer.builder()
                .balance(90.90)
                .costCenter("Costceter")
                .periodName("period")
                .subheaderItem("subdub")
                .vote("Yeah")
                .build();
        log.info(taxPayer.toString());
        taxPayerService.saveTaxPayer(taxPayer);

        TaxPayer taxPayer1 = taxPayerService.getTaxPayer(90.9).orElse(new TaxPayer());
        log.info(taxPayer1.toString());
        assertEquals("Yeah",taxPayer1.getVote());

    }
}
