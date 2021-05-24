/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.repo.SubjetsRepo
 * Last modified:  5/22/21, 6:26 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.repo;

import io.robbinespu.ess.model.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjetsRepo extends JpaRepository<Subjects, String> {
    @Query(value = "select nextval(SEQ_subject)", nativeQuery = true)
    public Long getNextValFromSeq();

    Optional<Subjects> findById(String id);
}
