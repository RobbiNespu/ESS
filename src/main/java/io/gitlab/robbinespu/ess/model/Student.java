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
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "name is required")
    @NotNull(message = "cannot be null")
    @NotBlank(message = "cannot be blank")
    private String name;

    private String department;
    @Column(nullable = true, name = "email")
    private String email;
}
