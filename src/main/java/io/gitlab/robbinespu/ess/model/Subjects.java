package io.gitlab.robbinespu.ess.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "subjects")
public class Subjects {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ess_generator")
    @SequenceGenerator(name = "ess_generator", sequenceName = "ess_generator", initialValue = 10)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String name;
    private int form;

}
