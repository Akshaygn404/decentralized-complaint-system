package com.akshay.dcrs.controller.escalation;

import com.akshay.dcrs.dto.response.escalation.EscalationResponse;
import com.akshay.dcrs.service.escalation.IEscalationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/complaints")
@RequiredArgsConstructor
public class EscalationController {

    private final IEscalationService escalationService;

    @GetMapping("/{complaintId}/escalations")
    public List<EscalationResponse> getEscalationHistory(
            @PathVariable String complaintId) {

        return escalationService.getEscalationHistory(complaintId);
    }
}