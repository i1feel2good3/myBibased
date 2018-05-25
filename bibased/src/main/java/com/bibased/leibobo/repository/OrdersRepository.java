package com.bibased.leibobo.repository;

import com.bibased.leibobo.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by boboLei on 2018/4/28.
 */
@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {

}
