package com.akshay.dcrs.dto.request.complaint;

import com.akshay.dcrs.model.enums.ComplaintStatus;
import lombok.Data;

@Data
public class ComplaintStatusUpdateRequest {
    private ComplaintStatus status;
}