package lk.quantacom.smarterpbackend.repository;

import feign.Param;
import lk.quantacom.smarterpbackend.dto.response.getBySalesReceiptAndSalesDepositResponse;
import lk.quantacom.smarterpbackend.dto.response.getChequeDepositAndBankAccountByChqueNoResponse;
import lk.quantacom.smarterpbackend.entity.SalesDeposit;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface SalesDepositRepository extends JpaRepository<SalesDeposit,Long> , JpaSpecificationExecutor {


    List<SalesDeposit> findByIsDeleted(Deleted deleted);

    List<SalesDeposit> findByCustomerIdAndBranchId(Integer customerId,String branchId);

    @Query(value = " select sd.id as sales_deposit_id,sd.date,sd.deposit_amount as sales_deposit_amount,c.name, " +
            " b.bank_name,b.branch_name,b.acc_name,sr.id as sales_receipt_id,sr.in_pay_date,sr.grand_total, " +
            " sr.deposit_amount as sales_receipt_deposit_amount " +
            " from smart_erp.sales_deposit sd inner join smart_erp.customer c on sd.customer_id=c.id  " +
            " inner join smart_erp.sales_receipt sr on sd.sales_receipt_id=sr.id  " +
            " inner join smart_erp.bank_account b on sd.bank_account_id=b.id  " +
            " where c.name =?1 && sd.date between ?2 and ?3  && sd.branch_id=?4 group by sd.id order by sr.id",nativeQuery = true)
    List<getBySalesReceiptAndSalesDepositResponse> getSalesDepositAndReceiptByCustomerName(String customerName,String startDate, String endDate, Integer branchId);


    @Query(value = " select sd.id as sales_deposit_id,sd.date,sd.deposit_amount as sales_deposit_amount,c.name, " +
            " b.bank_name,b.branch_name,b.acc_name,sr.id as sales_receipt_id,sr.in_pay_date,sr.grand_total, " +
            " sr.deposit_amount as sales_receipt_deposit_amount " +
            " from smart_erp.sales_deposit sd inner join smart_erp.customer c on sd.customer_id=c.id  " +
            " inner join smart_erp.sales_receipt sr on sd.sales_receipt_id=sr.id  " +
            " inner join smart_erp.bank_account b on sd.bank_account_id=b.id  " +
            " where b.bank_name =?1 && sd.date between ?2 and ?3  && sd.branch_id=?4 group by sd.id order by sr.id",nativeQuery = true)
    List<getBySalesReceiptAndSalesDepositResponse> getSalesDepositAndReceiptByBankName(String bankName,String startDate, String endDate, Integer branchId);

    @Query(value = " select sd.id as sales_deposit_id,sd.date,sd.deposit_amount as sales_deposit_amount,c.name, " +
            " b.bank_name,b.branch_name,b.acc_name,sr.id as sales_receipt_id,sr.in_pay_date,sr.grand_total, " +
            " sr.deposit_amount as sales_receipt_deposit_amount " +
            " from smart_erp.sales_deposit sd inner join smart_erp.customer c on sd.customer_id=c.id  " +
            " inner join smart_erp.sales_receipt sr on sd.sales_receipt_id=sr.id  " +
            " inner join smart_erp.bank_account b on sd.bank_account_id=b.id  " +
            " where b.branch_name =?1 && sd.date between ?2 and ?3  && sd.branch_id=?4 group by sd.id order by sr.id",nativeQuery = true)
    List<getBySalesReceiptAndSalesDepositResponse> getSalesDepositAndReceiptByBranchName(String branchName,String startDate, String endDate, Integer branchId);

    @Query(value = " select sd.id as sales_deposit_id,sd.date,sd.deposit_amount as sales_deposit_amount,c.name, " +
            " b.bank_name,b.branch_name,b.acc_name,sr.id as sales_receipt_id,sr.in_pay_date,sr.grand_total, " +
            " sr.deposit_amount as sales_receipt_deposit_amount " +
            " from smart_erp.sales_deposit sd inner join smart_erp.customer c on sd.customer_id=c.id  " +
            " inner join smart_erp.sales_receipt sr on sd.sales_receipt_id=sr.id  " +
            " inner join smart_erp.bank_account b on sd.bank_account_id=b.id  " +
            " where b.acc_no =?1 && sd.date between ?2 and ?3  && sd.branch_id=?4 group by sd.id order by sr.id",nativeQuery = true)
    List<getBySalesReceiptAndSalesDepositResponse> getSalesDepositAndReceiptByAccNo(String accNo,String startDate, String endDate, Integer branchId);

    @Query(value = " select sd.id as sales_deposit_id,sd.date,sd.deposit_amount as sales_deposit_amount,c.name, " +
            " b.bank_name,b.branch_name,b.acc_name,sr.id as sales_receipt_id,sr.in_pay_date,sr.grand_total, " +
            " sr.deposit_amount as sales_receipt_deposit_amount " +
            " from smart_erp.sales_deposit sd inner join smart_erp.customer c on sd.customer_id=c.id  " +
            " inner join smart_erp.sales_receipt sr on sd.sales_receipt_id=sr.id  " +
            " inner join smart_erp.bank_account b on sd.bank_account_id=b.id  " +
            " where b.acc_name =?1 && sd.date between ?2 and ?3  && sd.branch_id=?4 group by sd.id order by sr.id",nativeQuery = true)
    List<getBySalesReceiptAndSalesDepositResponse> getSalesDepositAndReceiptByAccName(String accName,String startDate, String endDate, Integer branchId);

    @Query(value = " select sd.id as sales_deposit_id,sd.date,sd.deposit_amount as sales_deposit_amount,c.name, " +
            " b.bank_name,b.branch_name,b.acc_name,sr.id as sales_receipt_id,sr.in_pay_date,sr.grand_total, " +
            " sr.deposit_amount as sales_receipt_deposit_amount " +
            " from smart_erp.sales_deposit sd inner join smart_erp.customer c on sd.customer_id=c.id  " +
            " inner join smart_erp.sales_receipt sr on sd.sales_receipt_id=sr.id  " +
            " inner join smart_erp.bank_account b on sd.bank_account_id=b.id  " +
            " where   sd.date between ?2 and ?3  && sd.branch_id=?4 group by sd.id order by sr.id",nativeQuery = true)
    List<getBySalesReceiptAndSalesDepositResponse> getSalesDepositAndReceiptByDateRange(String startDate, String endDate, Integer branchId);

}