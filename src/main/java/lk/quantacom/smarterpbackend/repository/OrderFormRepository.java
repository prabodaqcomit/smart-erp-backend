package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.OrderForm;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderFormRepository extends JpaRepository<OrderForm,Long> , JpaSpecificationExecutor {


    List<OrderForm> findByIsDeleted(Deleted deleted);

    @Query(value = "select max(order_id) from order_form",nativeQuery = true)
    Integer getMaxId();
}