package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.BranchNetwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.*;

import java.util.Date;
import java.util.List;

public interface BranchNetworkRepository extends JpaRepository<BranchNetwork,Long> , JpaSpecificationExecutor {

    @Query(value = "select max(branch_id) FROM branch_network",nativeQuery = true)
    String getMaxId();

    List<BranchNetwork> findByIsDeleted(Deleted deleted);

    List<BranchNetwork> findByBranchName(String branchName);

    List<BranchNetwork> findByBranchType(Long branchType);

    BranchNetwork findByIsDeletedAndDamageLocation(Deleted deleted,Boolean isDamage);

    @Query(value = "select count(id) from branch_network",nativeQuery = true)
    Integer getBranchCount();


    ;
 }