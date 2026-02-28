package com.akshay.dcrs.dto.response.escalation;



import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EscalationResponse {

    private Long escalationId;
    private String escalatedTo;
    private String reason;
    private LocalDateTime timestamp;
}