package com.application.JavaDataSetter.repository;

import com.application.JavaDataSetter.model.TaxPayer;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaxPayerRepo extends JpaRepository<TaxPayer,Long> {

    @Override
    <S extends TaxPayer> S save(S s);

    @Override
    <S extends TaxPayer> Optional<S> findOne(Example<S> example);

    Optional<TaxPayer> findFirstByBalance(double balance);
}
