package lk.quantacom.smarterpbackend.repository;

import feign.Param;
import lk.quantacom.smarterpbackend.dto.response.*;
import lk.quantacom.smarterpbackend.entity.ReceivedCheques;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface ReceivedChequesRepository extends JpaRepository<ReceivedCheques,Long> , JpaSpecificationExecutor {


    List<ReceivedCheques> findByIsDeleted(Deleted deleted);

    List<ReceivedCheques> findByCustomerId(Integer customerId);

    ReceivedCheques findByIdAndChequeNo(Long id,String chequeNo);

    ReceivedCheques findByIsDeletedAndChequeNo(Deleted deleted,String chequeNo);

    List<ReceivedCheques> findByChequeNo(String chequeNo);

    List<ReceivedCheques> findByBankCode(String bankCode);

    List<ReceivedCheques> findByBranchCode(String branchCode);

    @Query(value = "select c.depo_bank_id,b.bank_name,b.acc_no from  cheque_deposit c inner join  bank_account b on c.depo_bank_id=b.id  " +
            " inner join  received_cheques r on c.received_cheques_id=r.id where r.cheque_no=?1 group by r.cheque_no",nativeQuery = true)
    List<getChequeDepositAndBankAccountByChqueNoResponse> getByChequeDepositAndBankAccountByChequeNo(String chequeNo);

    @Query(value = "SELECT * FROM smart_erp.received_cheques WHERE customer_id=?1 and received_date between ?2 and ?3 " +
            " && branch_id=?4 order by cheque_date",nativeQuery = true)
    List<ReceivedChequesInterfaceResponse> getByCustomerIdAndDateAndBranchId(@Param("customerId") Integer customerId,@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("branchId") Integer branchId);

    @Query(value = "SELECT * FROM smart_erp.received_cheques WHERE cheque_no=?1 and received_date between ?2 and ?3 " +
            " && branch_id=?4 order by received_date",nativeQuery = true)
    List<ReceivedChequesInterfaceResponse> getByChequeNoAndDateAndBranchId(@Param("chequeNo") String chequeNo,@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("branchId") Integer branchId);

    @Query(value = "SELECT * FROM smart_erp.received_cheques WHERE bank_code=?1 and received_date between ?2 and ?3 " +
            " && branch_id=?4 order by received_date",nativeQuery = true)
    List<ReceivedChequesInterfaceResponse> getByBankCodeAndDateAndBranchId(@Param("bankCode") String bankCode,@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("branchId") Integer branchId);

    @Query(value = "SELECT * FROM smart_erp.received_cheques WHERE branch_code=?1 and received_date between ?2 and ?3 " +
            " && branch_id=?4 order by received_date",nativeQuery = true)
    List<ReceivedChequesInterfaceResponse> getByBranchCodeAndDateAndBranchId(@Param("branchCode") String branchCode,@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("branchId") Integer branchId);

    @Query(value = "select r.id,r.received_date,c.name,r.cheque_date,r.cheque_acc_name,r.cheque_no,r.this_is_pd,r.bank_code,r.branch_code,r.cheque_amount, " +
            " r.depo_date,r.depo_bank,r.remarks,r.status,r.new_cheque_no,r.status_date from smart_erp.received_cheques r inner join smart_erp.customer c  " +
            " on r.customer_id=c.id WHERE r.received_date between ?1 and ?2 AND r.branch_id=?3 order by r.received_date",nativeQuery = true)
    List<ReceivedChequesAndCustomerInterfaceResponse> getByChequesAndDateAndBranchId(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("branchId") Integer branchId);

    @Query(value = "select r.id,r.received_date,c.name,r.cheque_date,r.cheque_acc_name,r.cheque_no,r.this_is_pd,r.bank_code,r.branch_code,r.cheque_amount, " +
            " r.depo_date,r.depo_bank,r.remarks,r.status,r.new_cheque_no,r.status_date from smart_erp.received_cheques r inner join smart_erp.customer c  " +
            " on r.customer_id=c.id WHERE r.received_date between ?1 and ?2 AND r.branch_id=?3 order by r.cheque_date",nativeQuery = true)
    List<ReceivedChequesAndCustomerInterfaceResponse> getByChequeDateAndDateAndBranchId(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("branchId") Integer branchId);

    @Query(value = "select r.id,r.received_date,c.name,r.cheque_date,r.cheque_acc_name,r.cheque_no,r.this_is_pd,r.bank_code,r.branch_code,r.cheque_amount, " +
            " r.depo_date,r.depo_bank,r.remarks,r.status,r.new_cheque_no,r.status_date from smart_erp.received_cheques r inner join smart_erp.customer c  " +
            " on r.customer_id=c.id WHERE r.received_date between ?1 and ?2 AND r.branch_id=?3 order by r.depo_date",nativeQuery = true)
    List<ReceivedChequesAndCustomerInterfaceResponse> getByDepoDateAndDateAndBranchId(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("branchId") Integer branchId);

    @Query(value = "select r.id,r.received_date,c.name,r.cheque_date,r.cheque_acc_name,r.cheque_no,r.this_is_pd,r.bank_code,r.branch_code,r.cheque_amount, " +
            " r.depo_date,r.depo_bank,r.remarks,r.status,r.new_cheque_no,r.status_date from received_cheques r " +
            " inner join customer c on r.customer_id=c.id inner join cheque_deposit d ON r.id = d.received_cheques_id " +
            " where r.received_date between ?1 and ?2 and r.branch_id=?3 order by r.depo_date",nativeQuery = true)
    List<ReceivedChequesAndCustomerInterfaceResponse> getByDepositDateAndDateAndBranchId(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("branchId") Integer branchId);

    @Query(value = "select r.id, r.availble_amount, r.bank_code, r.branch_code, r.branch_id,r.cheque_acc_name, r.cheque_amount, r.cheque_date, r.cheque_no, r.currency_type, r.customer_id, r.depo_bank, r.depo_date, r.new_cheque_no, r.pd_owner,  " +
            " r.received_date, r.remarks, r.status, r.status_date, r.this_is_pd, c.address, c.avlb_credit_limit, c.credit_acc_no,  " +
            " c.credit_limit, c.email, c.fax, c.gender, c.image, c.name, c.nic_numbr, c.t_home, c.t_mobile, c.t_office, c.t_party_email,  " +
            " c.t_party_mobile, c.t_party_name, c.type, c.vat from received_cheques r inner join customer c on r.customer_id=c.id   " +
            " where  cheque_no=?1 order by r.id",nativeQuery = true)
    List<getReceivedChequesAndCustomerByChequeNoResponse> getByReceivedChequesAndCustomerByChequeNo(String chequeNo);

    @Query(value = "select c.depo_bank_id,b.bank_ledger_id from cheque_deposit c inner join bank_account b on c.depo_bank_id=b.id " +
            " inner join received_cheques r on c.received_cheques_id=r.id " +
            " inner join customer cu on c.customer_id =cu.id  where r.cheque_no =?1  group by r.cheque_no",nativeQuery = true)
    List<getBankIdAndLadgerIdResponse> getBygetBankIdAndLadgerIdByChequeNo(String chequeNo);

}
