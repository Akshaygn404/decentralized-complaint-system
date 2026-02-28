package com.akshay.dcrs.repository;

import com.akshay.dcrs.model.Escalation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EscalationRepository extends JpaRepository<Escalation, Long> {

    List<Escalation> findByComplaintIdOrderByTimestampAsc(Long complaintId);

    long countByComplaint_Id(Long complaintId);

    @Query("SELECT COUNT(DISTINCT e.complaint.id) FROM Escalation e")
    Long countEscalatedComplaints();
}