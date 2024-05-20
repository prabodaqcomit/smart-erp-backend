package lk.quantacom.smarterpbackend.repository;

import feign.Param;
import lk.quantacom.smarterpbackend.dto.response.*;
import lk.quantacom.smarterpbackend.entity.ChequeReturns;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface ChequeReturnsRepository extends JpaRepository<ChequeReturns,Long> , JpaSpecificationExecutor {

    @Query(value = "select max(id) from cheque_returns",nativeQuery = true)
    Long getMaxId();

    List<ChequeReturns> findByIsDeleted(Deleted deleted);

    List<ChequeReturns> findByCustomerIdAndBranchId(Integer customerId,String branchId);

    List<ChequeReturns> findByBankCode(String bankCode);

    List<ChequeReturns> findByBranchCode(String branchCode);

    @Query(value = "select o.return_date,c.payee_name,c.issue_date,c.update_window,o.remarks,o.bank_code,o.branch_code,o.cheque_no,o.value  " +
            "from own_cheque_returns o inner join  cheque_issue_note c on o.cheque_issue_note_id=c.id  " +
            "where date between  ?2 and ?3 && c.payee_name =?1  && o.branch_id=?4 group by c.id order by c.id",nativeQuery = true)
    List<getByOwnChequeReturnResponse> getByPayeeNameAndDateAndBranchId(String payeeName, String startDate, String endDate, Integer branchId);

    @Query(value = "select o.return_date,c.payee_name,c.issue_date,c.update_window,o.remarks,o.bank_code,o.branch_code,o.cheque_no,o.value  " +
            "from  own_cheque_returns o inner join  cheque_issue_note c on o.cheque_issue_note_id=c.id  " +
            "where date between  ?2 and ?3 && o.cheque_no =?1  && o.branch_id=?4 group by c.id order by c.id",nativeQuery = true)
    List<getByOwnChequeReturnResponse> getByChequeNoAndDateAndBranchId(String ChequeNO, String startDate, String endDate, Integer branchId);

    @Query(value = "select o.return_date,c.payee_name,c.issue_date,c.update_window,o.remarks,o.bank_code,o.branch_code,o.cheque_no,o.value  " +
            "from  own_cheque_returns o inner join  cheque_issue_note c on o.cheque_issue_note_id=c.id  " +
            "where date between  ?2 and ?3 && c.bank_code =?1  && o.branch_id=?4 group by c.id order by c.id",nativeQuery = true)
    List<getByOwnChequeReturnResponse> getByBankCodeAndDateAndBranchId(String bankCode, String startDate, String endDate, Integer branchId);

    @Query(value = "select o.return_date,c.payee_name,c.issue_date,c.update_window,o.remarks,o.bank_code,o.branch_code,o.cheque_no,o.value  " +
            "from  own_cheque_returns o inner join  cheque_issue_note c on o.cheque_issue_note_id=c.id  " +
            "where date between  ?2 and ?3 && c.branch_code =?1  && o.branch_id=?4 group by c.id order by c.id",nativeQuery = true)
    List<getByOwnChequeReturnResponse> getByBranchCodeAndDateAndBranchId(String branchCode, String startDate, String endDate, Integer branchId);


    @Query(value = "select d.depo_bank_id,r.cheque_no,b.bank_name,b.acc_no from cheque_deposit d inner join bank_account b on d.depo_bank_id=b.id inner join received_cheques r on d.received_cheques_id=r.id group by r.cheque_no",nativeQuery = true)
    List<CustomerChequeReturnSearch> getForCusChRtn();

    @Query(value = "select c.name,r.received_date,r.cheque_acc_name," +
            "r.cheque_date,r.currency_type,r.bank_code,r.branch_code,r.cheque_no,r.cheque_amount,r.status,r.status_date," +
            "c.id as customer_id,c.credit_acc_no from received_cheques r inner join customer c on r.customer_id=c.id where" +
            " cheque_no=?1 order by r.id",nativeQuery = true)
    List<GetReceivedCheckWiithCustomer> getFromReceivedCheque(String chequeNo);

    @Query(value = "select c.depo_bank_id,b.bank_ledger_id from cheque_deposit c inner join bank_account b on " +
            "c.depo_bank_id=b.id inner join received_cheques r on c.received_cheques_id=r.id " +
            "inner join customer t on c.customer_id =t.id  where r.cheque_no = ?1  group by r.cheque_no",nativeQuery = true)
    List<ChequeBankDetail> getDepoBankDetail(String chequeNo);


}