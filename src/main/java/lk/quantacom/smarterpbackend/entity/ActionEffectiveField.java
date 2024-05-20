package lk.quantacom.smarterpbackend.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ACTION_EFFECTIVE_FIELD")
@Data
public class ActionEffectiveField extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "ACTION_FIELD_ID", nullable = false)
    private Long actionFieldId;

//    @ManyToOne
//    @JoinColumn(name = "ACTION_FIELD_ID", nullable = false)
//    private ActionField actionFieldId;

    @Column(name = "EFFECTIVE_FIELD_ALIAS", length = 50, nullable = false)
    private String effectiveFieldAlias;

}