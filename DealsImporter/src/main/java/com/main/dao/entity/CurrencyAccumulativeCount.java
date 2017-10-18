/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Majid
 */
@Entity
@Table(name = "currencyaccumulativecount")
@Data
@Setter
@Getter
public class CurrencyAccumulativeCount {

  @Id
  @Column
  private String currencyIsoCode;

  @Column
  private Long countOfDeals;

  public CurrencyAccumulativeCount() {
  }

  public CurrencyAccumulativeCount(Object[] arr) {
    this.countOfDeals = new Long(arr[0].toString());
    this.currencyIsoCode = (String) arr[1];

  }

}
