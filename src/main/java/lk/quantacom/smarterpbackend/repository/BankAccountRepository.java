package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.BankAccount;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount,Long> , JpaSpecificationExecutor {

    List<BankAccount> findByIsDeleted(Deleted deleted);

    List<BankAccount> findByBankNameAndAccNoOrderByAccNo(String bankName,String accNo);

    List<BankAccount> findByAccNo(String accNo);

    BankAccount findByIsDeletedAndAccNo(Deleted deleted,String accNo);

    List<BankAccount> findByBankNameOrderByBankNameAsc(String bankName);

    List<BankAccount> findByBranchName(String branchName);

    List<BankAccount> findByAccName(String accName);

}


