package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.dto.response.ChequeDepoReq;
import lk.quantacom.smarterpbackend.entity.ChequeDeposit;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ChequeDepositRepository extends JpaRepository<ChequeDeposit, Long>, JpaSpecificationExecutor {


    List<ChequeDeposit> findByIsDeleted(Deleted deleted);

    @Query(value = "select r.cheque_no,d.depo_bank_id,a.bank_name,a.acc_no,d.date from cheque_deposit d inner join bank_account a on " +
            "d.depo_bank_id=a.id inner join received_cheques r on d.received_cheques_id=r.id " +
            " group by r.cheque_no", nativeQuery = true)
    List<ChequeDepoReq> getCh();


}