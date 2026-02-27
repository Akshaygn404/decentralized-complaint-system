package com.akshay.dcrs.service.complaint;

import com.akshay.dcrs.dto.request.complaint.ComplaintCreateRequest;
import com.akshay.dcrs.dto.request.complaint.ComplaintStatusUpdateRequest;
import com.akshay.dcrs.dto.response.complaint.ComplaintResponse;

public interface IComplaintService {

    ComplaintResponse createComplaint(ComplaintCreateRequest request);

    ComplaintResponse updateStatus(String complaintId, ComplaintStatusUpdateRequest request);
}