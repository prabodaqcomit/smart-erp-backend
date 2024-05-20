package lk.quantacom.smarterpbackend.repository;


import lk.quantacom.smarterpbackend.entity.MenuItems;
import lk.quantacom.smarterpbackend.entity.Profile;
import lk.quantacom.smarterpbackend.entity.ProfileMenuMapping;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfileMenuMappingRepository extends JpaRepository<ProfileMenuMapping,Long> , JpaSpecificationExecutor {

    @Query(value = "select * from profile_menu_mapping p where profile_id=?1 and is_deleted=0" +
            " and exists (select * from menu_items t where t.id=p.menu_id)",nativeQuery = true)
    List<ProfileMenuMapping> getByProfile(Integer id);

    List<ProfileMenuMapping> findByMenuAndIsDeleted(MenuItems menuItems, Deleted deleted);

    List<ProfileMenuMapping> findByProfileAndIsDeleted(Profile profile, Deleted deleted);

    List<ProfileMenuMapping> findByIsDeleted(Deleted deleted);


}