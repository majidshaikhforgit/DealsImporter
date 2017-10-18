package com.main.dao.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "validdeal")
@Data
@Setter
@Getter
public class ValidDeal implements Serializable {

  @Id
  @Column
  private Integer uniqueId;
  @Column
  private String fromCurrency, toCurrency, timestamp, amount, filename;

  public ValidDeal() {
  }

  public ValidDeal(Integer uniqueId, String fromCurrency, String toCurrency,
          String timestamp, String amount, String filename) {

    this.uniqueId = uniqueId;
    this.fromCurrency = fromCurrency;
    this.toCurrency = toCurrency;
    this.timestamp = timestamp;
    this.amount = amount;
    this.filename = filename;
  }
}
