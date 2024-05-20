package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.UnDepositedFundReferenceHeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.Deleted;

import java.util.Date;
import java.util.List;

public interface UnDepositedFundReferenceHeaderRepository extends JpaRepository<UnDepositedFundReferenceHeader, Long>, JpaSpecificationExecutor<UnDepositedFundReferenceHeader> {


    List<UnDepositedFundReferenceHeader> findByIsDeleted(Deleted deleted);

    @Query(
            value = "SELECT \n" +
                    "h.* \n" +
                    "FROM \n" +
                    "undeposited_fund_reference_header h \n" +
                    "WHERE \n" +
                    "h.is_deleted = 0 \n" +
                    "AND h.un_deposited_total_balance > 0 "
            ,nativeQuery = true
    )
    List<UnDepositedFundReferenceHeader> getPendingUnDepositedFundReference();

    @Query(
            value = "SELECT \n" +
                    "h.* \n" +
                    "FROM \n" +
                    "undeposited_fund_reference_header h \n" +
                    "WHERE\n" +
                    "h.is_deleted = 0 \n" +
                    "AND h.un_deposited_total_balance > 0 "
            ,countQuery =
                    "SELECT \n" +
                    "count(h.id) \n" +
                    "FROM \n" +
                    "undeposited_fund_reference_header h \n" +
                    "WHERE \n" +
                    "h.is_deleted = 0 \n" +
                    "AND h.un_deposited_total_balance > 0 "
            ,nativeQuery = true
    )
    Page<UnDepositedFundReferenceHeader> getPendingUnDepositedFundReference(Pageable pageable);

    @Query(
            value = "SELECT \n" +
                    "h.* \n" +
                    "FROM \n" +
                    "undeposited_fund_reference_header h \n" +
                    "WHERE\n" +
                    "h.is_deleted = 0 \n" +
                    "AND h.un_deposited_total_balance > 0 \n" +
                    "AND h.transaction_branch_id = 1?"
            ,nativeQuery = true
    )
    List<UnDepositedFundReferenceHeader> getPendingUnDepositedFundReferenceByBranch(Long branchId);

    @Query(
            value = "SELECT \n" +
                    "h.* \n" +
                    "FROM \n" +
                    "undeposited_fund_reference_header h \n" +
                    "WHERE\n" +
                    "h.is_deleted = 0 \n" +
                    "AND h.un_deposited_total_balance > 0 \n" +
                    "AND h.transaction_branch_id = 1?"
            ,countQuery =
                    "SELECT \n" +
                    "count(h.id) \n" +
                    "FROM \n" +
                    "undeposited_fund_reference_header h \n" +
                    "WHERE\n" +
                    "h.is_deleted = 0 \n" +
                    "AND h.un_deposited_total_balance > 0 \n" +
                    "AND h.transaction_branch_id = 1?"
            ,nativeQuery = true
    )
    Page<UnDepositedFundReferenceHeader> getPendingUnDepositedFundReferenceByBranch(Long branchId, Pageable pageable);




    @Query(
            value = "SELECT  \n" +
                    "   DATE_ADD(  \n" +
                    "       IFNULL(MAX(udfh.transaction_date), STR_TO_DATE('2024-02-01', '%Y-%m-%d'))  \n" +
                    "       , INTERVAL -1 MONTH  \n" +
                    "   )  transaction_date  \n" +
                    "FROM  \n" +
                    "undeposited_fund_reference_header udfh  \n" +
                    "WHERE udfh.transaction_is_invoice = 1"
            ,nativeQuery = true
    )
    Date getUnSavedInvoiceLastCheckDate();

    @Query(
            value = "SELECT \n" +
                    "DATE_ADD( \n" +
                    "       IFNULL(MAX(udfh.transaction_date), STR_TO_DATE('2024-02-01', '%Y-%m-%d')) \n" +
                    "   , INTERVAL -1 MONTH \n" +
                    "   ) transaction_date \n" +
                    "FROM \n" +
                    "undeposited_fund_reference_header udfh \n" +
                    "WHERE udfh.transaction_is_receipt = 1"
            ,nativeQuery = true
    )
    Date getUnSavedReceiptLastCheckDate();

    /*
    @Query(
            value = "SELECT \n" +
                    "NULL id, NULL created_by, NULL created_date_time, 0 is_deleted, NULL modified_by, NULL modified_date_time, \n" +
                    "h.fld_Location transaction_branch_id, \n" +
                    "h.fld_InvNo transaction_id, \n" +
                    "h.fld_Date transaction_date, \n" +
                    "true transaction_is_invoice, \n" +
                    "false transaction_is_receipt, \n" +
                    "'Invoice' transaction_type_description, \n" +
                    "MAX(h.fld_NetAmount) transaction_amount, \n" +
                    "SUM(pd.fld_PayTypeAmt) un_deposited_total_amount, \n" +
                    "SUM(pd.fld_PayTypeAmt) un_deposited_total_balance \n" +
                    "FROM \n" +
                    "tblinvhed h \n" +
                    "INNER JOIN tblpaydtl pd ON \n" +
                    "h.fld_Location = pd.fld_Location AND h.fld_InvNo = pd.fld_InvNo \n" +
                    "AND pd.fld_PayType IN ( 'cash', 'cheque' ) \n" +
                    "LEFT OUTER JOIN undeposited_fund_reference_header udfh ON \n" +
                    "udfh.transaction_is_invoice = 1 \n" +
                    "AND h.fld_Location = udfh.transaction_branch_id AND h.fld_InvNo = udfh.transaction_id \n" +
                    "WHERE \n" +
                    "udfh.id IS NULL \n" +
                    "AND h.fld_Date > ?1 \n" +
                    "GROUP BY h.fld_Location, h.fld_InvNo \n" +
                    "ORDER BY h.fld_Location, h.fld_InvNo"
            ,nativeQuery = true
    )
    List<UnDepositedFundReferenceHeader> getUnSavedInvoiceUnDepositedFundHeaders(Date lastCheckedDate);

    @Query(
            value = "SELECT \n" +
                    "NULL id, NULL created_by, NULL created_date_time, 0 is_deleted, NULL modified_by, NULL modified_date_time, \n" +
                    "rh.branch_id transaction_branch_id, \n" +
                    "rh.id transaction_id, \n" +
                    "rh.transaction_date transaction_date, \n" +
                    "false transaction_is_invoice, \n" +
                    "true transaction_is_receipt, \n" +
                    "'Receipt' transaction_type_description, \n" +
                    "MAX(rh.total_amount) transaction_amount, \n" +
                    "SUM(rpm.paid_amount) un_deposited_total_amount, \n" +
                    "SUM(rpm.paid_amount) un_deposited_total_balance \n" +
                    "FROM \n" +
                    "receipt_header rh \n" +
                    "INNER JOIN receipt_payment_method rpm ON \n" +
                    "rh.id = rpm.receipt_header_id \n" +
                    "LEFT OUTER JOIN undeposited_fund_reference_header udfh ON \n" +
                    "rh.branch_id = udfh.transaction_branch_id AND rh.id = udfh.transaction_id \n" +
                    "WHERE \n" +
                    "udfh.id IS NULL \n" +
                    "AND rh.transaction_date > ?1 \n" +
                    "GROUP BY rh.branch_id, rh.id \n" +
                    "ORDER BY rh.branch_id, rh.id"
            ,nativeQuery = true
    )
    List<UnDepositedFundReferenceHeader> getUnSavedReceiptUnDepositedFundHeaders(Date lastCheckedDate);
    */

}