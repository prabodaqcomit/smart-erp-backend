package lk.quantacom.smarterpbackend.repository;


import lk.quantacom.smarterpbackend.entity.ActionEffectiveField;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActionEffectiveFieldRepository extends JpaRepository<ActionEffectiveField,Long> , JpaSpecificationExecutor {


    List<ActionEffectiveField> findByIsDeleted(Deleted deleted);

    @Modifying
    @Query("delete from ActionEffectiveField where actionFieldId=?1" )
    Integer deleteByAction(Long actionId);

}