package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.ChequeBookRegister;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface ChequeBookRegisterRepository extends JpaRepository<ChequeBookRegister,Long> , JpaSpecificationExecutor {


    List<ChequeBookRegister> findByIsDeleted(Deleted deleted);

    @Query(value = "select max(cheque_book_register_id) from cheque_book_register",nativeQuery = true)
    Integer getMaxChequeBookRegId();

    List<ChequeBookRegister> findByBankAccountId(String bankAccountId);

}