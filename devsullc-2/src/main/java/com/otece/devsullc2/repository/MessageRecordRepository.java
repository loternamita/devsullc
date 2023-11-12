package com.otece.devsullc2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.otece.devsullc2.entity.MessageRecord;

public interface MessageRecordRepository extends JpaRepository<MessageRecord, Long> {
    @Query("SELECT mr FROM MessageRecord mr WHERE mr.content = :parameter")
    MessageRecord findByContent(@Param("parameter") String content);
}
