package io.gitlab.robbinespu.ess.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @SequenceGenerator(name = "SEQ_form", sequenceName = "SEQ_form", initialValue = 1, allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotEmpty(message = "name is required")
    @NotNull(message = "cannot be null")
    @NotBlank(message = "cannot be blank")
    private String name;
    private int form;
}
