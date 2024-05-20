package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.DiningTableTmpHead;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DiningTableTmpHeadRepository extends JpaRepository<DiningTableTmpHead,Long> , JpaSpecificationExecutor {


    List<DiningTableTmpHead> findByIsDeleted(Deleted deleted);

    DiningTableTmpHead  findByTableIdAndInvStatusAndIsDeleted(Long tid,Integer inSt,Deleted deleted);
}