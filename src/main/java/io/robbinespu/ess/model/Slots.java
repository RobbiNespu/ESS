/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.model.Slots
 * Last modified:  5/29/21, 2:46 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.robbinespu.ess.util.CustomSeqGeneratorIdForUser;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

  private String bookedBy;
  private Date bookedDate;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "classSubjectList_Id")
  @JsonManagedReference
  private ClassSubjectList classSubjectList;

  private boolean active;
  private boolean booked;
  private Date examDate;
  private String name;
}
