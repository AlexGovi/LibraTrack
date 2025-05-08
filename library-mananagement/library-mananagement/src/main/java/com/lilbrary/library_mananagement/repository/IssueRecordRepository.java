package com.lilbrary.library_mananagement.repository;

import com.lilbrary.library_mananagement.entity.IssueRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRecordRepository extends JpaRepository<IssueRecord,Long> {
}
