package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "GENERAL_JOURNAL_REVERSE")
@Data
public class GeneralJournalReverse extends BaseEntity {

    @Column(name = "REVERSE_FOR_JOURNAL_ID", nullable = false)
    private Long reverseForJournalId;

    @Column(name = "REVERSE_FOR_JOURNAL_NUMBER", nullable = false)
    private String reverseForJournalNumber;

    @Column(name = "REVERSE_AT_JOURNAL_ID", nullable = false)
    private Long reverseAtJournalId;

    @Column(name = "REVERSE_AT_JOURNAL_NUMBER", nullable = false)
    private String reverseAtJournalNumber;
}