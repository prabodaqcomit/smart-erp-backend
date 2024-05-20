package lk.quantacom.smarterpbackend.repository;


import lk.quantacom.smarterpbackend.entity.ActionType;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ActionTypeRepository extends JpaRepository<ActionType,Long> , JpaSpecificationExecutor {


    List<ActionType> findByIsDeleted(Deleted deleted);


}