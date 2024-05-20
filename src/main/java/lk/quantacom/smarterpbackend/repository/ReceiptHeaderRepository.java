package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.ReceiptHeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.Deleted;

import java.util.Date;
import java.util.List;

public interface ReceiptHeaderRepository extends JpaRepository<ReceiptHeader, Long>, JpaSpecificationExecutor {


    @Query(value = "select receipt_number from receipt_header " +
            " where id = (select max(id) from receipt_header)", nativeQuery = true)
    String getMaximumReceiptNumber();

    List<ReceiptHeader> findByIsDeleted(Deleted deleted, Sort sort);

    List<ReceiptHeader> findByIsDeletedAndBranchId(Deleted deleted, Long branchId, Sort sort);

    @Query(
            value = "SELECT DISTINCT h.receipt_number FROM receipt_header h WHERE h.is_deleted = 0 \n" +
                    "AND CASE WHEN ?1 IS NOT NULL THEN h.receipt_number LIKE %?1% ELSE TRUE END"
            ,nativeQuery = true
    )
    List<String> getAvailableReceiptNumbers(String searchNumber);



    //-------------------------------Paginated----------------------------------------------------------------


    @Query(value =
            "SELECT h.* FROM receipt_header h \n" +
                    "INNER JOIN customer c ON h.customer_id = c.id \n" +
                    "WHERE  \n" +
                    "h.is_deleted = 0  \n" +
                    "AND h.customer_id <> NULL \n" +
                    "AND c.is_deleted = 0 "
            ,countQuery =
            "SELECT count(h.id) FROM receipt_header h \n" +
                    "INNER JOIN customer c ON h.customer_id = c.id \n" +
                    "WHERE  \n" +
                    "h.is_deleted = 0  \n" +
                    "AND h.customer_id <> NULL \n" +
                    "AND c.is_deleted = 0 "
            ,nativeQuery = true)
    Page<ReceiptHeader> getCustomerReceipts(
            Pageable pageable
    );

    @Query(value =
            "SELECT h.* FROM receipt_header h \n" +
                    "INNER JOIN customer c ON h.customer_id = c.id \n" +
                    "WHERE  \n" +
                    "h.is_deleted = 0  \n" +
                    "AND h.customer_id <> NULL \n" +
                    "AND c.is_deleted = 0 \n" +
                    "AND h.branch_id = ?1 "
            ,countQuery =
            "SELECT count(h.id) FROM receipt_header h \n" +
                    "INNER JOIN customer c ON h.customer_id = c.id \n" +
                    "WHERE  \n" +
                    "h.is_deleted = 0  \n" +
                    "AND h.customer_id <> NULL \n" +
                    "AND c.is_deleted = 0 \n" +
                    "AND h.branch_id = ?1 "
            ,nativeQuery = true)
    Page<ReceiptHeader> getCustomerReceipts(
            Long branchId,
            Pageable pageable
    );

    @Query(value =
            "SELECT h.* FROM receipt_header h \n" +
                    "WHERE  \n" +
                    "h.is_deleted = 0  \n" +
                    "AND h.customer_id IS NULL"
            ,countQuery =
            "SELECT count(h.id) FROM receipt_header h \n" +
                    "WHERE  \n" +
                    "h.is_deleted = 0  \n" +
                    "AND h.customer_id IS NULL"
            ,nativeQuery = true)
    Page<ReceiptHeader> getGeneralReceipts(
            Pageable pageable
    );

    @Query(value =
            "SELECT h.* FROM receipt_header h \n" +
                    "WHERE  \n" +
                    "h.is_deleted = 0  \n" +
                    "AND h.customer_id IS NULL \n" +
                    "AND h.branch_id = ?1 "
            ,countQuery =
            "SELECT count(h.id) FROM receipt_header h \n" +
                    "WHERE  \n" +
                    "h.is_deleted = 0  \n" +
                    "AND h.customer_id IS NULL \n" +
                    "AND h.branch_id = ?1 "
            ,nativeQuery = true)
    Page<ReceiptHeader> getGeneralReceipts(
            Long branchId,
            Pageable pageable
    );

    @Query(value =
            "SELECT h.* FROM receipt_header h \n" +
                    "INNER JOIN customer c ON h.customer_id = c.id \n" +
                    "WHERE  \n" +
                    "h.is_deleted = 0  \n" +
                    "AND h.customer_id <> NULL \n" +
                    "AND c.is_deleted = 0 \n" +
                    "AND CASE WHEN ?1 IS NOT NULL THEN h.transaction_date BETWEEN ?1 AND ?2 ELSE TRUE END \n" +
                    "AND CASE WHEN ?3 IS NOT NULL THEN h.customer_id = ?3 ELSE TRUE END \n" +
                    "AND CASE WHEN ?4 IS NOT NULL THEN h.total_amount = ?4 ELSE TRUE END \n" +
                    "AND CASE WHEN ?5 IS NOT NULL THEN h.receipt_number = ?5 ELSE TRUE END \n"
            ,countQuery =
            "SELECT count(h.id) FROM receipt_header h \n" +
                    "INNER JOIN customer c ON h.customer_id = c.id \n" +
                    "WHERE  \n" +
                    "h.is_deleted = 0  \n" +
                    "AND h.customer_id <> NULL \n" +
                    "AND c.is_deleted = 0 \n" +
                    "AND CASE WHEN ?1 IS NOT NULL THEN h.transaction_date BETWEEN ?1 AND ?2 ELSE TRUE END \n" +
                    "AND CASE WHEN ?3 IS NOT NULL THEN h.customer_id = ?3 ELSE TRUE END \n" +
                    "AND CASE WHEN ?4 IS NOT NULL THEN h.total_amount = ?4 ELSE TRUE END \n" +
                    "AND CASE WHEN ?5 IS NOT NULL THEN h.receipt_number = ?5 ELSE TRUE END \n"
            ,nativeQuery = true)
    Page<ReceiptHeader> searchCustomFilterCustomerReceipts(
            Date transactionFromDate, Date transactionToDate,
            Long customerId,
            Double amount,
            String receiptNumber,
            Pageable pageable
            //Long ResultOffset,
            //Integer ResultLimit
    );

    @Query(value =
            "SELECT h.* FROM receipt_header h \n" +
                    "INNER JOIN customer c ON h.customer_id = c.id \n" +
                    "WHERE  \n" +
                    "h.is_deleted = 0  \n" +
                    "AND h.customer_id <> NULL \n" +
                    "AND c.is_deleted = 0 \n" +
                    "AND h.branch_id = ?1 \n " +
                    "AND CASE WHEN ?2 IS NOT NULL THEN h.transaction_date BETWEEN ?2 AND ?3 ELSE TRUE END \n" +
                    "AND CASE WHEN ?4 IS NOT NULL THEN h.customer_id = ?4 ELSE TRUE END \n" +
                    "AND CASE WHEN ?5 IS NOT NULL THEN h.total_amount = ?5 ELSE TRUE END \n" +
                    "AND CASE WHEN ?6 IS NOT NULL THEN h.receipt_number = ?6 ELSE TRUE END \n"
            ,countQuery =
            "SELECT count(h.id) FROM receipt_header h \n" +
                    "INNER JOIN customer c ON h.customer_id = c.id \n" +
                    "WHERE  \n" +
                    "h.is_deleted = 0  \n" +
                    "AND h.customer_id <> NULL \n" +
                    "AND c.is_deleted = 0 \n" +
                    "AND h.branch_id = ?1 \n " +
                    "AND CASE WHEN ?2 IS NOT NULL THEN h.transaction_date BETWEEN ?2 AND ?3 ELSE TRUE END \n" +
                    "AND CASE WHEN ?4 IS NOT NULL THEN h.customer_id = ?4 ELSE TRUE END \n" +
                    "AND CASE WHEN ?5 IS NOT NULL THEN h.total_amount = ?5 ELSE TRUE END \n" +
                    "AND CASE WHEN ?6 IS NOT NULL THEN h.receipt_number = ?6 ELSE TRUE END \n"
            ,nativeQuery = true)
    Page<ReceiptHeader> searchCustomFilterCustomerReceipts(
            Long branchId,
            Date transactionFromDate, Date transactionToDate,
            Long customerId,
            Double amount,
            String receiptNumber,
            Pageable pageable
            //Long ResultOffset,
            //Integer ResultLimit
    );

    @Query(value =
            "SELECT h.* FROM receipt_header h \n" +
                    "WHERE  \n" +
                    "h.is_deleted = 0  \n" +
                    "AND h.customer_id IS NULL \n" +
                    "AND CASE WHEN ?1 IS NOT NULL THEN h.transaction_date BETWEEN ?1 AND ?2 ELSE TRUE END \n" +
                    "AND CASE WHEN ?3 IS NOT NULL THEN h.customer_id = ?3 ELSE TRUE END \n" +
                    "AND CASE WHEN ?4 IS NOT NULL THEN h.total_amount = ?4 ELSE TRUE END \n" +
                    "AND CASE WHEN ?5 IS NOT NULL THEN h.receipt_number = ?5 ELSE TRUE END \n"
            ,countQuery =
            "SELECT count(h.id) FROM receipt_header h \n" +
                    "WHERE  \n" +
                    "h.is_deleted = 0  \n" +
                    "AND h.customer_id IS NULL \n" +
                    "AND CASE WHEN ?1 IS NOT NULL THEN h.transaction_date BETWEEN ?1 AND ?2 ELSE TRUE END \n" +
                    "AND CASE WHEN ?3 IS NOT NULL THEN h.customer_id = ?3 ELSE TRUE END \n" +
                    "AND CASE WHEN ?4 IS NOT NULL THEN h.total_amount = ?4 ELSE TRUE END \n" +
                    "AND CASE WHEN ?5 IS NOT NULL THEN h.receipt_number = ?5 ELSE TRUE END \n"
            ,nativeQuery = true)
    Page<ReceiptHeader> searchCustomFilterGeneralReceipts(
            Date transactionFromDate, Date transactionToDate,
            Long payerContactId,
            Double amount,
            String receiptNumber,
            Pageable pageable
            //Long ResultOffset,
            //Integer ResultLimit
    );

    @Query(value =
            "SELECT h.* FROM receipt_header h \n" +
                    "WHERE  \n" +
                    "h.is_deleted = 0  \n" +
                    "AND h.customer_id IS NULL \n" +
                    "AND h.branch_id = ?1 \n " +
                    "AND CASE WHEN ?2 IS NOT NULL THEN h.transaction_date BETWEEN ?2 AND ?3 ELSE TRUE END \n" +
                    "AND CASE WHEN ?4 IS NOT NULL THEN h.customer_id = ?4 ELSE TRUE END \n" +
                    "AND CASE WHEN ?5 IS NOT NULL THEN h.total_amount = ?5 ELSE TRUE END \n" +
                    "AND CASE WHEN ?6 IS NOT NULL THEN h.receipt_number = ?6 ELSE TRUE END \n"
            ,countQuery =
            "SELECT count(h.id) FROM receipt_header h \n" +
                    "WHERE  \n" +
                    "h.is_deleted = 0  \n" +
                    "AND h.customer_id IS NULL \n" +
                    "AND h.branch_id = ?1 \n " +
                    "AND CASE WHEN ?2 IS NOT NULL THEN h.transaction_date BETWEEN ?2 AND ?3 ELSE TRUE END \n" +
                    "AND CASE WHEN ?4 IS NOT NULL THEN h.customer_id = ?4 ELSE TRUE END \n" +
                    "AND CASE WHEN ?5 IS NOT NULL THEN h.total_amount = ?5 ELSE TRUE END \n" +
                    "AND CASE WHEN ?6 IS NOT NULL THEN h.receipt_number = ?6 ELSE TRUE END \n"
            ,nativeQuery = true)
    Page<ReceiptHeader> searchCustomFilterGeneralReceipts(
            Long branchId,
            Date transactionFromDate, Date transactionToDate,
            Long payerContactId,
            Double amount,
            String receiptNumber,
            Pageable pageable
            //Long ResultOffset,
            //Integer ResultLimit
    );


    //-------------------------------Paginated----------------------------------------------------------------

}