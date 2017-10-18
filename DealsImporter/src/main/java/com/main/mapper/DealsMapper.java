/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.mapper;

import com.main.dao.dto.Deal;
import com.main.dao.entity.InvalidDeal;
import com.main.dao.entity.ValidDeal;
import com.main.service.DealsImporterServiceImpl;

/**
 *
 * @author Majid
 */
public interface DealsMapper {
  static Deal createDeal(String line) {
    String[] p = line.split(",");// a CSV has comma separated lines
    return new Deal(new Integer(p[0]), p[1], p[2], p[3], p.length == 5 ? p[4] : null, DealsImporterServiceImpl.fileName);
  }

  static ValidDeal mapToValidDeal(Deal d) {
    return new ValidDeal(d.getUniqueId(), d.getFromCurrency(), d.getToCurrency(), d.getTimestamp(), d.getAmount(), DealsImporterServiceImpl.fileName);
  }

  static InvalidDeal mapToInvalidDeal(Deal d) {
    return new InvalidDeal(d.getUniqueId(), d.getFromCurrency(), d.getToCurrency(), d.getTimestamp(), d.getAmount(), DealsImporterServiceImpl.fileName);
  }
}
