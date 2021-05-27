/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.model.Users
 * Last modified:  5/25/21, 2:09 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.model;

import io.robbinespu.ess.util.CustomSeqGeneratorIdForUser;
import io.robbinespu.ess.util.DbAuditModels;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users extends DbAuditModels {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_user")
  @Column(name = "id", updatable = false, nullable = false)
  @GenericGenerator(
      name = "SEQ_user",
      strategy = "io.robbinespu.ess.util.CustomSeqGeneratorIdForUser",
      parameters = {
        @Parameter(name = CustomSeqGeneratorIdForUser.INCREMENT_PARAM, value = "50"),
        @Parameter(name = CustomSeqGeneratorIdForUser.VALUE_PREFIX_PARAMETER, value = "user_"),
        @Parameter(name = CustomSeqGeneratorIdForUser.NUMBER_FORMAT_PARAMETER, value = "%03d")
      })
  private String id;

  @NotEmpty(message = "name is required")
  @NotNull(message = "cannot be null")
  @NotBlank(message = "cannot be blank")
  private String name;

  private String department;

  @Column(nullable = true, name = "email")
  private String email;

  private String username;
  private String password;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "rolesId")
  private Roles roles;
}
