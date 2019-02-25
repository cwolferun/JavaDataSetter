package com.application.JavaDataSetter.service;

import com.application.JavaDataSetter.model.TaxPayer;
import com.application.JavaDataSetter.repository.TaxPayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaxPayerService {

    private final
    TaxPayerRepo taxPayerRepo;

    @Autowired
    public TaxPayerService(TaxPayerRepo taxPayerRepo) {
        this.taxPayerRepo = taxPayerRepo;
    }


    public void saveTaxPayer(TaxPayer taxPayer){

        taxPayerRepo.save(taxPayer);
    }

    public Optional<TaxPayer> getTaxPayer(double balance){
        return taxPayerRepo.findFirstByBalance(balance);
    }


}
