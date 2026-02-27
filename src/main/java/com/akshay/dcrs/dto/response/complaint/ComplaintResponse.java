package com.akshay.dcrs.dto.response.complaint;

import com.akshay.dcrs.model.enums.ComplaintStatus;
import com.akshay.dcrs.model.enums.Priority;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ComplaintResponse {

    private String complaintId;
    private ComplaintStatus status;
    private Priority priority;
    private LocalDateTime slaDeadline;
    private String department;
    private String ward;
}