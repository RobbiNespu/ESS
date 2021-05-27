/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.model.Slots
 * Last modified:  5/28/21, 3:07 AM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.model;

import io.robbinespu.ess.util.CustomSeqGeneratorIdForUser;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "slots")
public class Slots implements Serializable {
  // User-defined SerialVersionUID
  private static final long serialVersionUID = 42L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_slot")
  @GenericGenerator(
      name = "SEQ_slot",
      strategy = "io.robbinespu.ess.util.CustomSeqGeneratorIdForUser",
      parameters = {
        @org.hibernate.annotations.Parameter(
            name = CustomSeqGeneratorIdForUser.INCREMENT_PARAM,
            value = "50"),
        @org.hibernate.annotations.Parameter(
            name = CustomSeqGeneratorIdForUser.VALUE_PREFIX_PARAMETER,
            value = "slot_"),
        @org.hibernate.annotations.Parameter(
            name = CustomSeqGeneratorIdForUser.NUMBER_FORMAT_PARAMETER,
            value = "%03d")
      })
  private String id;

  private String name;
  private int classId;
  private int subjectId;
  private boolean booked;
  private boolean active;
  private Date examDate;
}
