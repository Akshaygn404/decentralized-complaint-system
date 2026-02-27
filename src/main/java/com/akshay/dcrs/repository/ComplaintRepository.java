package com.akshay.dcrs.repository;

import com.akshay.dcrs.model.Complaint;
import com.akshay.dcrs.model.enums.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    Optional<Complaint> findByComplaintId(String complaintId);

    List<Complaint> findByStatusNotInAndSlaDeadlineBefore(
            List<ComplaintStatus> statuses,
            LocalDateTime time
    );
}