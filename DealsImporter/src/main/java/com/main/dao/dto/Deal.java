package com.main.dao.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Data
@Setter
@Getter
public class Deal {

  private Integer uniqueId;

  private String fromCurrency, toCurrency, timestamp, amount, filename;

  private boolean isValid;

  public Deal() {
  }

  public Deal(Integer uniqueId, String fromCurrency, String toCurrency,
          String timestamp, String amount, String filename) {
    super();
    this.uniqueId = uniqueId;
    this.fromCurrency = fromCurrency;
    this.toCurrency = toCurrency;
    this.timestamp = timestamp;
    this.amount = amount;
    this.filename = filename;
    if (!StringUtils.isEmpty(amount)) {
      isValid = true;
    }
  }

}
