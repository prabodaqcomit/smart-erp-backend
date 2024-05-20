package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.StockTakeProces;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface StockTakeProcesRepository extends JpaRepository<StockTakeProces,Integer> , JpaSpecificationExecutor {


    List<StockTakeProces> findByIsDeleted(Deleted deleted);

    @Query(value = "select max(pending_process_id) from stock_take_proces",nativeQuery = true)
    Integer getMaxId();

    List<StockTakeProces> findByStockProcesAndIsDeleted(Boolean stockProces,Deleted deleted);

    @Modifying
    @Query(value = " UPDATE stock SET stores_qty = 0,total_qty = 0 WHERE modified_date_time < CURDATE() ",nativeQuery = true)
    Integer updateStock();

    @Modifying
    @Query(value = " UPDATE stock s INNER JOIN item_master i ON s.item_code=i.item_code SET s.stores_qty = 0, s.total_qty=0 " +
            "  WHERE s.modified_date_time < CURDATE() AND i.category_id=2 ",nativeQuery = true)
    Integer updateStockByCat();

    @Query(value = "select sum(physical_qty) from stock_take_proces where stock_proces=false and is_deleted=0 and physical_qty>0 ",nativeQuery = true)
    Double getPhysicalQty();

    @Query(value = "select sum(total_qty) from stock_take_proces where stock_proces=false and is_deleted=0 and total_qty>0",nativeQuery = true)
    Double getStoresPlusQty();

    @Query(value = "select sum(total_qty) from stock_take_proces where stock_proces=false and is_deleted=0 and total_qty<0",nativeQuery = true)
    Double getStoresMinusQty();
}