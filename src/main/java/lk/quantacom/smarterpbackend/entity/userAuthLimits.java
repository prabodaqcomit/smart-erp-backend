package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "USER_AUTH_LIMITS")
@Data
public class userAuthLimits extends BaseEntity {

    @Column(nullable = false)
    private String username;

    @Column(name = "PO_AUTH_LIMIT", nullable = false)
    private Double poAuthLimit;

    @Column(name = "PO_AUTH_NEXT_USER", nullable = false)
    private String poAuthNextUser;

    @Column(name = "GRN_AUTH_LIMIT", nullable = false)
    private Double grnAuthLimit;

    @Column(name = "GRN_AUTH_NEXT_USER", nullable = false)
    private String grnAuthNextUser;

}