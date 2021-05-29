/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.repo.NodeRepo
 * Last modified:  5/29/21, 4:35 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.repo;

import io.robbinespu.ess.model.Nodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NodeRepo extends JpaRepository<Nodes, String> {
    @Query(value = "SELECT COUNT(*) FROM `nodes` WHERE `parent` = :searchSubjectId", nativeQuery = true)
    int sizeParentSubject(@Param("searchSubjectId") String searchSubjectId);

    @Query(value = "SELECT COUNT(*) FROM `nodes` WHERE `parent` = :searchSubjectId", nativeQuery = true)
    int sizeChildSubject(@Param("searchSubjectId") String searchSubjectId);
}
