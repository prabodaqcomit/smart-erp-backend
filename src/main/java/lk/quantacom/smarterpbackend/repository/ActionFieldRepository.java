package lk.quantacom.smarterpbackend.repository;


import lk.quantacom.smarterpbackend.entity.ActionField;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ActionFieldRepository extends JpaRepository<ActionField,Long> , JpaSpecificationExecutor {


    List<ActionField> findByIsDeleted(Deleted deleted);

    ActionField findByActionAliasAndReferenceAliasAndIsDeleted(String alias,String refAlias,Deleted deleted);

    List<ActionField> findByIsNumericAndReferenceAliasAndIsDeleted(Boolean numeric,String ref,Deleted deleted);
}