/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.dao.jpa;

import com.main.dao.entity.ValidDeal;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author webwerks
 */
@Repository
public interface ValidDealRepository extends CrudRepository<ValidDeal, Long> {

  @Query(value = "select sum(tb.count) as currencycount , tb.from_currency as fromCurrency from (select count(valid.unique_id) as count,valid.from_currency \n"
          + "from validdeal as valid \n"
          + "group by valid.from_currency \n"
          + "union all\n"
          + "select count(invalid.unique_id) as count,invalid.from_currency \n"
          + "from invaliddeal as invalid \n"
          + "group by invalid.from_currency ) as tb\n"
          + "group by tb.from_currency", nativeQuery = true)
  List<Object[]> getFromCurrencyAccumulativeCount();

  Long countByfilename(String filename);

}
