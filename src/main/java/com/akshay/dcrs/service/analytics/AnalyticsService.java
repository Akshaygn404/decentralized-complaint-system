package com.akshay.dcrs.service.analytics;

import com.akshay.dcrs.dto.response.analytics.SummaryResponse;
import com.akshay.dcrs.model.Complaint;
import com.akshay.dcrs.repository.ComplaintRepository;
import com.akshay.dcrs.repository.EscalationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsService implements IAnalyticsService {

    private final ComplaintRepository complaintRepository;

    private final EscalationRepository escalationRepository;

    @Override
    public List<SummaryResponse> getDepartmentSummary() {

        return complaintRepository.countComplaintsByDepartment()
                .stream()
                .map(obj -> new SummaryResponse(
                        (String) obj[0],
                        (Long) obj[1]
                ))
                .toList();
    }

    @Override
    public List<SummaryResponse> getWardSummary() {

        return complaintRepository.countComplaintsByWard()
                .stream()
                .map(obj -> new SummaryResponse(
                        (String) obj[0],
                        (Long) obj[1]
                ))
                .toList();
    }

    @Override
    public Double getEscalationRate() {

        Long total = complaintRepository.countTotalComplaints();
        Long escalated = escalationRepository.countEscalatedComplaints();

        if (total == 0) return 0.0;

        return (escalated * 100.0) / total;
    }

    @Override
    public Double getAverageResolutionTime() {

        List<Complaint> resolvedComplaints =
                complaintRepository.findResolvedComplaints();

        if (resolvedComplaints.isEmpty()) return 0.0;

        double totalHours = resolvedComplaints.stream()
                .mapToDouble(c ->
                        java.time.Duration
                                .between(c.getCreatedAt(), c.getUpdatedAt())
                                .toHours()
                )
                .sum();

        return totalHours / resolvedComplaints.size();
    }
}