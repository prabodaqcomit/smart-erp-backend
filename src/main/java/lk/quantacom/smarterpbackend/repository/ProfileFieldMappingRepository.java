package lk.quantacom.smarterpbackend.repository;


import lk.quantacom.smarterpbackend.entity.ActionField;
import lk.quantacom.smarterpbackend.entity.Profile;
import lk.quantacom.smarterpbackend.entity.ProfileFieldMapping;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfileFieldMappingRepository extends JpaRepository<ProfileFieldMapping,Long> , JpaSpecificationExecutor {

    @Query(value = "select * from profile_field_mapping p where profile_id=?1 and is_deleted=0" +
            " and exists (select * from action_field t where t.id=p.action_field_id) ",nativeQuery = true)
    List<ProfileFieldMapping> getByProfile(Integer id);

    List<ProfileFieldMapping> findByProfileAndIsDeleted(Profile profile, Deleted deleted);

    List<ProfileFieldMapping> findByActionFieldAndIsDeleted(ActionField tabForm, Deleted deleted);

    ProfileFieldMapping findByProfileAndActionFieldAndIsDeleted(Profile profile, ActionField tabForm, Deleted deleted);

    List<ProfileFieldMapping> findByIsDeleted(Deleted deleted);


}