package com.akshay.dcrs.scheduler;

import com.akshay.dcrs.model.enums.ComplaintStatus;
import com.akshay.dcrs.model.AuditLog;
import com.akshay.dcrs.model.Complaint;
import com.akshay.dcrs.model.User;
import com.akshay.dcrs.repository.AuditLogRepository;
import com.akshay.dcrs.repository.ComplaintRepository;
import com.akshay.dcrs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SlaEscalationScheduler {

    private final ComplaintRepository complaintRepository;
    private final AuditLogRepository auditLogRepository;
    private final UserRepository userRepository;

    @Scheduled(fixedRate = 60000) // runs every 1 minute
    public void escalateOverdueComplaints() {

        List<Complaint> overdueComplaints =
                complaintRepository.findByStatusNotInAndSlaDeadlineBefore(
                        List.of(ComplaintStatus.CLOSED, ComplaintStatus.RESOLVED, ComplaintStatus.ESCALATED),
                        LocalDateTime.now()
                );

        for (Complaint complaint : overdueComplaints) {

            ComplaintStatus previousStatus = complaint.getStatus();

            complaint.setStatus(ComplaintStatus.ESCALATED);
            complaint.setUpdatedAt(LocalDateTime.now());

            complaintRepository.save(complaint);

            // SYSTEM user for escalation
            User systemUser = userRepository.findByEmail("system@dcrs.com")
                    .orElse(null);

            AuditLog auditLog = AuditLog.builder()
                    .complaint(complaint)
                    .previousStatus(previousStatus)
                    .newStatus(ComplaintStatus.ESCALATED)
                    .updatedBy(systemUser)
                    .timestamp(LocalDateTime.now())
                    .build();

            auditLogRepository.save(auditLog);
        }
    }
}