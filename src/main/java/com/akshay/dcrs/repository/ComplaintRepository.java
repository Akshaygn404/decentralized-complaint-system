package com.akshay.dcrs.repository;

import com.akshay.dcrs.model.Complaint;
import com.akshay.dcrs.model.enums.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    Optional<Complaint> findByComplaintId(String complaintId);

    List<Complaint> findByStatusNotInAndSlaDeadlineBefore(
            List<ComplaintStatus> statuses,
            LocalDateTime time
    );

    @Query("""
       SELECT c.department.name, COUNT(c)
       FROM Complaint c
       GROUP BY c.department.name
       """)
    List<Object[]> countComplaintsByDepartment();

    @Query("""
       SELECT c.ward.name, COUNT(c)
       FROM Complaint c
       GROUP BY c.ward.name
       """)
    List<Object[]> countComplaintsByWard();

    @Query("SELECT COUNT(c) FROM Complaint c")
    Long countTotalComplaints();

    @Query("""
       SELECT c
       FROM Complaint c
       WHERE c.status IN ('RESOLVED', 'CLOSED')
       """)
    List<Complaint> findResolvedComplaints();
}