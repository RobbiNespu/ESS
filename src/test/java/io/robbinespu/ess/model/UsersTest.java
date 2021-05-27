/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.model.UsersTest
 * Last modified:  5/22/21, 1:30 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UsersTest {

  @Test
  @DisplayName("Set name - String")
  public void setNameNotBlank() {
    Users std = new Users();
    std.setName("Ayam");
    assertEquals("Ayam", std.getName());
  }

  @Test
  @DisplayName("Set name - null")
  public void setNameBlank() {
    Users std = new Users();
    std.setName(null);
    assertNull(std.getName());
  }
}
