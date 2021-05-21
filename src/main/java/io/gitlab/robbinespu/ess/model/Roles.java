package io.gitlab.robbinespu.ess.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @SequenceGenerator(name = "SEQ_role", sequenceName = "SEQ_role")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String type;
    private String userId;
    private String form;

}