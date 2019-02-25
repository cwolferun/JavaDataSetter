package com.application.JavaDataSetter.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "TAXPAYERS")
@Entity
public class TaxPayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
   private long id;
    private String costCenter;
    private String subheaderItem;
    private double balance;
    private String periodName;
    private String vote;
}
