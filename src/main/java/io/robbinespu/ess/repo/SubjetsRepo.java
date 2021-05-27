/*
 * Copyright (c) 2021-2021
 *
 * Project :  Advance Software Development - Exam Scheduling System with DFS
 * Class name :  io.robbinespu.ess.repo.SubjetsRepo
 * Last modified:  5/27/21, 6:30 PM
 * User : Robbi Nespu < robbinespu@gmail.com >
 *
 * License : https://github.com/RobbiNespu/ESS/LICENSE
 */

package io.robbinespu.ess.repo;

import io.robbinespu.ess.model.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjetsRepo extends JpaRepository<Subjects, String> {
    @Query(value = "select nextval(SEQ_subject)", nativeQuery = true)
    public Long getNextValFromSeq();

    Optional<Subjects> findById(String id);

    @Query(value = "SELECT * FROM `subjects` WHERE `form` = :searchForm AND `name` = :searchSubjectName LIMIT 1", nativeQuery = true)
    Optional<Subjects> findByFormAndName(@Param("searchForm") Integer form, @Param("searchSubjectName") String subjectName);
}
