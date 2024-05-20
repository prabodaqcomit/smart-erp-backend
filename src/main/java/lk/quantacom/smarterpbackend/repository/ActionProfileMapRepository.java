package lk.quantacom.smarterpbackend.repository;


import lk.quantacom.smarterpbackend.dto.response.getActionFieldByReferenceAliasAndProfileIdResponse;
import lk.quantacom.smarterpbackend.entity.ActionProfileMap;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActionProfileMapRepository extends JpaRepository<ActionProfileMap,Long> , JpaSpecificationExecutor {


    List<ActionProfileMap> findByIsDeleted(Deleted deleted);

    List<ActionProfileMap> findByActionFieldIdAndIsDeleted(Long actionFieldId,Deleted deleted);

    @Modifying
    @Query("delete from ActionProfileMap where actionFieldId=?1" )
    Integer deleteByAction(Long actionId);

    @Query(value = " select a.id, a.created_by, a.created_date_time, a.is_deleted, a.modified_by, a.modified_date_time, a.action_alias, a.action_description, a.is_hidden,  " +
            " a.is_input_upper_case, a.read_only, a.reference_alias, a.field_event, a.formula, a.action_type_id,p.action_field_id,p.profile_id  " +
            " from  action_field a inner join action_profile_map p on a.id=p.action_field_id where p.profile_id=?1 and a.reference_alias=?2 ",nativeQuery = true)
    List<getActionFieldByReferenceAliasAndProfileIdResponse> getByActionFieldIdAndProfileId(Long profileId , String referenceAlias);
}