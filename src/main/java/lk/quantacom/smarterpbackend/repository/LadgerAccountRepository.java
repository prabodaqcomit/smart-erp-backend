package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.LadgerAccount;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LadgerAccountRepository extends JpaRepository<LadgerAccount,Long> , JpaSpecificationExecutor {

    @Query(value = "SELECT MAX(own_no) FROM ladger_account where account_category='ASSETS' and acc_type='CONVERTIBILITY - CURRENT ASSET' and sub_acc_type='ACCOUNTS RECEIVABLES'",nativeQuery = true)
    String getMaxOwnNo();

    @Query(value = "SELECT MAX(own_no) FROM ladger_account where account_category='LIABILITY' and acc_type='CURRENT LIABILITIES (SHORT-TERM LIABILITIES)' and sub_acc_type='ACCOUNTS PAYABLES'",nativeQuery = true)
    String getMaxOwnNoSup();

    @Query(value = "SELECT MAX(own_no) FROM ladger_account where account_category='ASSETS' and acc_type='CONVERTIBILITY - CURRENT ASSET' and sub_acc_type='BANK'",nativeQuery = true)
    String getMaxOwnNoBank();

    LadgerAccount findByAccNo(String accNo);

    @Query(value = "select * from ladger_account where account_category=?1 order by acc_name",nativeQuery = true)
    List<LadgerAccount> getByCategory(String category);


    @Query(value = "select * from ladger_account where is_deleted = 0 order by cast(acc_no as unsigned) asc",nativeQuery = true)
    List<LadgerAccount> getAllByDesc();

    List<LadgerAccount> findByIsDeletedOrderByAccNoDesc(Deleted deleted);

    List<LadgerAccount> findByIsDeletedAndAccountCategoryOrderByAccNoDesc(Deleted deleted,String accountCategory);

    List<LadgerAccount> findByAccNameOrderByAccNoDesc(String accName);

    List<LadgerAccount> findByAccountCategoryAndAccTypeAndAccNameOrderByAccNoDesc(String accountCategory,String accType,String accName);

    List<LadgerAccount> findByAccountCategoryAndAccNameOrderByAccNoDesc(String accountCategory,String accName);

    List<LadgerAccount> findByIsDeletedAndSubAccType(Deleted deleted, String subAccType);

    List<LadgerAccount> findByIsDeletedAndAccNo(Deleted deleted,String accNo);

    @Query(value = "select * from ladger_account where acc_name like '%?1%' and is_deleted = 0", nativeQuery = true)
    List<LadgerAccount> doFindByIsDeletedAndAccNameContaining(String accName);

 }