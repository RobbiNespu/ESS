/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.model.ClassSubjectList
 * Last modified:  5/25/21, 12:42 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.model;

import io.robbinespu.ess.util.CustomSeqGeneratorIdForUser;
import io.robbinespu.ess.util.DbAuditModels;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "class_subject_lists")
public class ClassSubjectList extends DbAuditModels {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_class_subject_lists")
  @GenericGenerator(
      name = "SEQ_class_subject_lists",
      strategy = "io.robbinespu.ess.util.CustomSeqGeneratorIdForUser",
      parameters = {
        @org.hibernate.annotations.Parameter(
            name = CustomSeqGeneratorIdForUser.INCREMENT_PARAM,
            value = "50"),
        @org.hibernate.annotations.Parameter(
            name = CustomSeqGeneratorIdForUser.VALUE_PREFIX_PARAMETER,
            value = "class_subject_lists_"),
        @org.hibernate.annotations.Parameter(
            name = CustomSeqGeneratorIdForUser.NUMBER_FORMAT_PARAMETER,
            value = "%03d")
      })
  private String id;

  private String formId;
  private String subjectId;
  private String teacherRoleId;
}
