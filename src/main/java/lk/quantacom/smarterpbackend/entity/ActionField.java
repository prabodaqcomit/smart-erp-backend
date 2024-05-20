package lk.quantacom.smarterpbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ACTION_FIELD")
@Data
public class ActionField extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "ACTION_ALIAS", length = 50, nullable = false)
    private String actionAlias;

    @Column(name = "ACTION_DESCRIPTION", nullable = false)
    private String actionDescription;

    @Column(name = "READ_ONLY", nullable = false)
    private Boolean readOnly;

    @Column(name = "IS_INPUT_UPPER_CASE", nullable = false)
    private Boolean isInputUpperCase;

    @Column(name = "IS_HIDDEN", nullable = false)
    private Boolean isHidden;

    @Column(name = "IS_NUMERIC", nullable = false)
    private Boolean isNumeric;

    @Column(name = "IS_MANDATORY", nullable = false)
    private Boolean isMandatory=false;

    @Column(name = "REFERENCE_ALIAS", length = 50, nullable = false)
    private String referenceAlias;

    @Column(name = "FIELD_EVENT", length = 30, nullable = false)
    private String fieldEvent;

    @Column(name = "FORMULA", nullable = false)
    private String formula;

    @Column(name = "ACTION_TYPE_ID", nullable = false)
    private Long actionTypeId;

    @OneToMany(mappedBy = "actionFieldId")
    private List<ActionEffectiveField> actionEffectiveFields;

}
