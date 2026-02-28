package com.akshay.dcrs.service.escalation;

import com.akshay.dcrs.dto.response.escalation.EscalationResponse;
import com.akshay.dcrs.model.Complaint;
import com.akshay.dcrs.model.Escalation;
import com.akshay.dcrs.repository.ComplaintRepository;
import com.akshay.dcrs.repository.EscalationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EscalationService implements IEscalationService {

    private final ComplaintRepository complaintRepository;
    private final EscalationRepository escalationRepository;

    @Override
    public List<EscalationResponse> getEscalationHistory(String complaintId) {

        Complaint complaint = complaintRepository
                .findByComplaintId(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        List<Escalation> escalations =
                escalationRepository.findByComplaintIdOrderByTimestampAsc(complaint.getId());

        return escalations.stream()
                .map(e -> EscalationResponse.builder()
                        .escalationId(e.getId())
                        .escalatedTo(e.getEscalatedTo().getEmail())
                        .reason(e.getReason())
                        .timestamp(e.getTimestamp())
                        .build())
                .toList();
    }
}