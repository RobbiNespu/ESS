/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.model.level
 * Last modified:  6/19/21, 11:49 AM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "level")
public class Level implements Serializable {

  @Id
  @Column(name = "level_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  private String power;

  @ManyToMany(targetEntity = Users.class, mappedBy = "level", cascade = CascadeType.ALL)
  private Set<Users> users = new HashSet<>();
}
