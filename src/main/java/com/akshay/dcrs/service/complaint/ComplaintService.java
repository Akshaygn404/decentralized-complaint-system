package com.akshay.dcrs.service.complaint;

import com.akshay.dcrs.dto.request.complaint.ComplaintCreateRequest;
import com.akshay.dcrs.dto.response.complaint.ComplaintResponse;
import com.akshay.dcrs.model.enums.ComplaintStatus;
import com.akshay.dcrs.model.enums.Priority;
import com.akshay.dcrs.model.*;
import com.akshay.dcrs.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComplaintService implements IComplaintService {

    private final ComplaintRepository complaintRepository;
    private final WardRepository wardRepository;
    private final AuditLogRepository auditLogRepository;
    private final UserRepository userRepository;

    @Override
    public ComplaintResponse createComplaint(ComplaintCreateRequest request) {


        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));


        Priority priority = detectPriority(request.getDescription());


        Ward ward = wardRepository
                .findByLatitudeMinLessThanEqualAndLatitudeMaxGreaterThanEqualAndLongitudeMinLessThanEqualAndLongitudeMaxGreaterThanEqual(
                        request.getLatitude(),
                        request.getLatitude(),
                        request.getLongitude(),
                        request.getLongitude()
                )
                .orElseThrow(() -> new RuntimeException("Ward not found"));

        Department department = ward.getDepartment();


        int slaHours = department.getDefaultSlaHours();
        if (priority == Priority.HIGH) {
            slaHours = slaHours / 2;
        }

        LocalDateTime slaDeadline = LocalDateTime.now().plusHours(slaHours);


        Complaint complaint = Complaint.builder()
                .complaintId("CMP-" + UUID.randomUUID().toString().substring(0, 8))
                .title(request.getTitle())
                .description(request.getDescription())
                .category(request.getCategory())
                .priority(priority)
                .status(ComplaintStatus.NEW)
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .ward(ward)
                .department(department)
                .slaDeadline(slaDeadline)
                .createdAt(LocalDateTime.now())
                .build();

        complaintRepository.save(complaint);


        AuditLog auditLog = AuditLog.builder()
                .complaint(complaint)
                .previousStatus(null)
                .newStatus(ComplaintStatus.NEW)
                .updatedBy(user)
                .timestamp(LocalDateTime.now())
                .build();

        auditLogRepository.save(auditLog);


        return ComplaintResponse.builder()
                .complaintId(complaint.getComplaintId())
                .status(complaint.getStatus())
                .priority(complaint.getPriority())
                .slaDeadline(complaint.getSlaDeadline())
                .department(department.getName())
                .ward(ward.getName())
                .build();
    }

    private Priority detectPriority(String description) {

        String lower = description.toLowerCase();

        if (lower.contains("accident") ||
                lower.contains("urgent") ||
                lower.contains("danger")) {
            return Priority.HIGH;
        }

        return Priority.MEDIUM;
    }
}