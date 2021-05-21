package io.robbinespu.ess.model;

import io.robbinespu.ess.util.CustomSeqGeneratorIdForUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "forms")
public class Forms {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_form")
    @GenericGenerator(
            name = "SEQ_form",
            strategy = "io.robbinespu.ess.util.CustomSeqGeneratorIdForUser",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = CustomSeqGeneratorIdForUser.INCREMENT_PARAM, value = "50"),
                    @org.hibernate.annotations.Parameter(name = CustomSeqGeneratorIdForUser.VALUE_PREFIX_PARAMETER, value = "form_"),
                    @org.hibernate.annotations.Parameter(name = CustomSeqGeneratorIdForUser.NUMBER_FORMAT_PARAMETER, value = "%03d")})
    private String id;
    @NotEmpty(message = "name is required")
    @NotNull(message = "cannot be null")
    @NotBlank(message = "cannot be blank")
    private String name;
    private int form;
}
