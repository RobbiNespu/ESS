/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.repo.NodeRepo
 * Last modified:  5/26/21, 2:12 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.repo;

import io.robbinespu.ess.model.Nodes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeRepo extends JpaRepository<Nodes, String> {
}
