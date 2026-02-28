package com.akshay.dcrs.scheduler;

import com.akshay.dcrs.model.AuditLog;
import com.akshay.dcrs.model.Complaint;
import com.akshay.dcrs.model.Escalation;
import com.akshay.dcrs.model.User;
import com.akshay.dcrs.model.enums.ComplaintStatus;
import com.akshay.dcrs.model.enums.Role;
import com.akshay.dcrs.repository.AuditLogRepository;
import com.akshay.dcrs.repository.ComplaintRepository;
import com.akshay.dcrs.repository.EscalationRepository;
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
    private final EscalationRepository escalationRepository;

    @Scheduled(fixedRate = 60000)
    public void escalateOverdueComplaints() {

        List<Complaint> overdueComplaints =
                complaintRepository.findByStatusNotInAndSlaDeadlineBefore(
                        List.of(
                                ComplaintStatus.CLOSED,
                                ComplaintStatus.RESOLVED
                        ),
                        LocalDateTime.now()
                );

        if (overdueComplaints.isEmpty()) {
            return;
        }


        User systemUser = userRepository.findByEmail("system@dcrs.com")
                .orElseThrow(() -> new RuntimeException("System user not found"));

        for (Complaint complaint : overdueComplaints) {

            long escalationCount =
                    escalationRepository.countByComplaint_Id(complaint.getId());

            User escalatedTo;

            if (escalationCount == 0) {
                escalatedTo = complaint.getDepartment().getAdmin();
            } else if (escalationCount == 1) {
                escalatedTo = userRepository.findByRole(Role.SUPER_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Super Admin not found"));
            } else {
                continue;
            }

            ComplaintStatus previousStatus = complaint.getStatus();

            complaint.setStatus(ComplaintStatus.ESCALATED);
            complaint.setUpdatedAt(LocalDateTime.now());
            complaintRepository.save(complaint);

            Escalation escalation = Escalation.builder()
                    .complaint(complaint)
                    .escalatedFrom(null)
                    .escalatedTo(escalatedTo)
                    .reason("SLA breached - Level " + (escalationCount + 1))
                    .timestamp(LocalDateTime.now())
                    .build();

            escalationRepository.save(escalation);

            AuditLog auditLog = AuditLog.builder()
                    .complaint(complaint)
                    .previousStatus(previousStatus)
                    .newStatus(ComplaintStatus.ESCALATED)
                    .updatedBy(escalatedTo)
                    .timestamp(LocalDateTime.now())
                    .build();

            auditLogRepository.save(auditLog);
        }
    }
}