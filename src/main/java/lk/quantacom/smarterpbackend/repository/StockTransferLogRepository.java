package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.StockTransferLog;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface StockTransferLogRepository extends JpaRepository<StockTransferLog,Long> , JpaSpecificationExecutor {


    List<StockTransferLog> findByIsDeleted(Deleted deleted);

    @Query(value = "select max(issue_number) FROM stock_transfer_log",nativeQuery = true)
    Integer getMaxId();

    List<StockTransferLog> findByIssueNumberAndIsDeleted(Integer issueNumber,Deleted deleted);

//    List<StockTransferLog> StockTransferLogGroupByIssueNumber(Integer issueNumber,Deleted deleted);

//    List<StockTransferLog> findByIsDeletedGroupByIssueNumber(Deleted deleted);

    StockTransferLog findByUnitPriceAndSizeAndItemCodeAndIssueNumberAndIsDeleted(Double unitPrice,Long size,String itemCode,Integer issueNumber,Deleted deleted);

    @Query(value = "select * from stock_transfer_log group by issue_number order by issue_number desc",nativeQuery = true)
    List<StockTransferLog> getAllIssueDetails();




}