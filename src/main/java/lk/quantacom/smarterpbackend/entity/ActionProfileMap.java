package lk.quantacom.smarterpbackend.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "ACTION_PROFILE_MAP")
@Data
public class ActionProfileMap extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    // Id of action field table
    @Column(name = "ACTION_FIELD_ID", nullable = false)
    private Long actionFieldId;

    // id of the profile
    @Column(name = "PROFILE_ID", length = 50, nullable = false)
    private Long profileId;

}

