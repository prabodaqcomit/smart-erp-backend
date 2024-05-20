package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.PettyCash;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PettyCashRepository extends JpaRepository<PettyCash,Long> , JpaSpecificationExecutor {


    List<PettyCash> findByIsDeleted(Deleted deleted);

    List<PettyCash> findByPayeeName(String payeeName);

    List<PettyCash> findByDescription(String desctription);

    @Query(value = "select * from petty_cash where date_format(petty_cash_date, '%Y-%m-%d') = date_format(?1, '%Y-%m-%d') " +
            "    and branch_id=?2 group by idpetty_cash,voucher_no order by id,voucher_no desc",nativeQuery = true)
    List<PettyCash> getByDateAndBranchId(String Date,Integer branchId);


    @Query(value = "Select * from smart_erp.petty_cash where petty_cash_date between ?1 and ?2 " +
            "  && payee_name=?3  && petty_cash.branch_id=?4  order by id ASC",nativeQuery = true)
    List<PettyCash> getByPayeeNameAndDateAndBranchId(String payeeName,String startDate,String endDate,Integer branchId);


    @Query(value = "Select *,SUM(no_semi_paying_amount) as paid_amount,SUM(postage) AS TOT_POST,SUM(stationary) AS TOT_STATI,SUM(travel)  " +
            "AS TOT_TRAV,SUM(foods) AS TOT_FOODS,SUM(entartainment) AS TOT_ENTER,SUM(other_ex) AS TOT_OTHER from petty_cash  " +
            "where petty_cash_date between ?1 and ?2 && petty_cash.branch_id=?3 group by payee_name order by payee_name",nativeQuery = true)
    List<PettyCash> getAllByDateAndBranchId(String startDate,String endDate,Integer branchId);


    @Query(value = "select * from petty_cash where petty_cash_date between ?1 and ?2 " +
            "   && petty_cash.branch_id=?3 order by idpetty_cash ASC",nativeQuery = true)
    List<PettyCash> getByDateRangeAndBranchId(String startDate,String endDate,Integer branchId);

}