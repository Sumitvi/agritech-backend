package com.example.bakend.trade.repository;
import com.example.bakend.trade.entity.Trade;
import com.example.bakend.trade.entity.TradeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, Long> {

    List<Trade> findByFarmerId(Long farmerId);

    List<Trade> findByTraderId(Long traderId);


    long countByStatus(TradeStatus status);
    List<Trade> findByStatus(TradeStatus status);


}
