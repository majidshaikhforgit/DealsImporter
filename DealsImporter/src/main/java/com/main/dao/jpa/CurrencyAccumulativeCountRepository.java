/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.dao.jpa;

import com.main.dao.entity.CurrencyAccumulativeCount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author webwerks
 */
@Repository
public interface CurrencyAccumulativeCountRepository extends CrudRepository<CurrencyAccumulativeCount, Long> {
}
