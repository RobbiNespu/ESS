/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.model.Roles
 * Last modified:  5/27/21, 3:59 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.model;

import io.robbinespu.ess.util.CustomSeqGeneratorIdForUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Roles implements Serializable {
  // User-defined SerialVersionUID
  private static final long SerialVersionUID = 1l;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_role")
  @GenericGenerator(
          name = "SEQ_role",
          strategy = "io.robbinespu.ess.util.CustomSeqGeneratorIdForUser",
          parameters = {
                  @org.hibernate.annotations.Parameter(
            name = CustomSeqGeneratorIdForUser.INCREMENT_PARAM,
            value = "50"),
        @org.hibernate.annotations.Parameter(
            name = CustomSeqGeneratorIdForUser.VALUE_PREFIX_PARAMETER,
            value = "role_"),
        @org.hibernate.annotations.Parameter(
            name = CustomSeqGeneratorIdForUser.NUMBER_FORMAT_PARAMETER,
            value = "%03d")
      })
  private String id;

  private String type;
  private String userId;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "forms_Id")
  private Forms forms;
}
