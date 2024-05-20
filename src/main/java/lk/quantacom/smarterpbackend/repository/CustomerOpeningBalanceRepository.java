package lk.quantacom.smarterpbackend.repository;

import feign.Param;
import lk.quantacom.smarterpbackend.dto.response.CustomerAndCusOpeningBalResponse;
import lk.quantacom.smarterpbackend.entity.CustomerOpeningBalance;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface CustomerOpeningBalanceRepository extends JpaRepository<CustomerOpeningBalance,Integer> , JpaSpecificationExecutor {

    @Query(value = "select max(customer_opening_balance_id) from customer_opening_balance",nativeQuery = true)
    Integer getMaxCuOpBalId();

    List<CustomerOpeningBalance> findByIsDeleted(Deleted deleted);

    @Query(value = "SELECT o.fld_customer_id FROM customer_opening_balance o " +
            " where o.fld_customer_id=?1",nativeQuery = true)
    String getFldCustomerId(@Param("fldCustomerId") String fldCustomerId);

    @Query(value = "select c.name,o.customer_opening_balance_id,o.fld_customer_id,o.fld_date,o.fld_due_balance,o.fld_net_amount,o.fld_paid_amount " +
            " from customer_opening_balance o inner join customer c on o.fld_customer_id = c.id",nativeQuery = true)
    List<CustomerAndCusOpeningBalResponse> getCustomerAndCusOpeningBal();
}