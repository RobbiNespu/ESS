/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.model.Nodes
 * Last modified:  5/28/21, 3:07 AM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.model;

import io.robbinespu.ess.util.CustomSeqGeneratorIdForUser;
import java.io.Serializable;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nodes")
public class Nodes implements Serializable {
  // User-defined SerialVersionUID
  private static final long serialVersionUID = 42L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_node")
  @GenericGenerator(
      name = "SEQ_node",
      strategy = "io.robbinespu.ess.util.CustomSeqGeneratorIdForUser",
      parameters = {
        @org.hibernate.annotations.Parameter(
            name = CustomSeqGeneratorIdForUser.INCREMENT_PARAM,
            value = "50"),
        @org.hibernate.annotations.Parameter(
            name = CustomSeqGeneratorIdForUser.VALUE_PREFIX_PARAMETER,
            value = "node_"),
        @org.hibernate.annotations.Parameter(
            name = CustomSeqGeneratorIdForUser.NUMBER_FORMAT_PARAMETER,
            value = "%03d")
      })
  private String id;

  private String parent;
  private String child;
  private int level;
}
