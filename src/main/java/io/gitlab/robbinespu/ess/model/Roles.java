package io.gitlab.robbinespu.ess.model;

import io.gitlab.robbinespu.ess.util.CustomSeqGeneratorIdForUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_role")
    @GenericGenerator(
            name = "SEQ_role",
            strategy = "io.gitlab.robbinespu.ess.util.CustomSeqGeneratorIdForUser",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = CustomSeqGeneratorIdForUser.INCREMENT_PARAM, value = "50"),
                    @org.hibernate.annotations.Parameter(name = CustomSeqGeneratorIdForUser.VALUE_PREFIX_PARAMETER, value = "role_"),
                    @org.hibernate.annotations.Parameter(name = CustomSeqGeneratorIdForUser.NUMBER_FORMAT_PARAMETER, value = "%03d")})
    private String id;
    private String type;
    private String userId;
    private String form;
}