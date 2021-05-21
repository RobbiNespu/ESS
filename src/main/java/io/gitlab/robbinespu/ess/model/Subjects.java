package io.gitlab.robbinespu.ess.model;

import io.gitlab.robbinespu.ess.util.CustomSeqGeneratorIdForUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "subjects")
public class Subjects {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_subject")
    @SequenceGenerator(name = "SEQ_subject", sequenceName = "SEQ_subject", initialValue = 1, allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    @GenericGenerator(
            name = "SEQ_subject",
            strategy = "io.gitlab.robbinespu.ess.util.CustomSeqGeneratorIdForUser",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = CustomSeqGeneratorIdForUser.INCREMENT_PARAM, value = "50"),
                    @org.hibernate.annotations.Parameter(name = CustomSeqGeneratorIdForUser.VALUE_PREFIX_PARAMETER, value = "subject_"),
                    @org.hibernate.annotations.Parameter(name = CustomSeqGeneratorIdForUser.NUMBER_FORMAT_PARAMETER, value = "%03d")})
    private String id;
    private String name;
    private int form;
}
