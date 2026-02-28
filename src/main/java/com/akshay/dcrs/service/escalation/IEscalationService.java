package com.akshay.dcrs.service.escalation;

import com.akshay.dcrs.dto.response.escalation.EscalationResponse;

import java.util.List;

public interface IEscalationService {

    List<EscalationResponse> getEscalationHistory(String complaintId);
}