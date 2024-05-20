package lk.quantacom.smarterpbackend.repository;


import lk.quantacom.smarterpbackend.entity.Profile;
import lk.quantacom.smarterpbackend.entity.ProfileTabFormMap;
import lk.quantacom.smarterpbackend.entity.TabForm;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfileTabFormMapRepository extends JpaRepository<ProfileTabFormMap,Long> , JpaSpecificationExecutor {

    @Query(value = "select * from profile_tab_form_map p where profile_id=?1 and is_deleted=0" +
            " and exists (select * from tab_form t where t.id=p.tab_form_id)  ",nativeQuery = true)
    List<ProfileTabFormMap> getByProfile(Integer id);

    List<ProfileTabFormMap> findByProfileAndIsDeleted(Profile profile, Deleted deleted);

    List<ProfileTabFormMap> findByTabFormAndIsDeleted(TabForm tabForm, Deleted deleted);

    ProfileTabFormMap findByProfileAndTabFormAndIsDeleted(Profile profile, TabForm tabForm, Deleted deleted);

    List<ProfileTabFormMap> findByIsDeleted(Deleted deleted);



}