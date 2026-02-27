package com.akshay.dcrs.controller.complaint;

import com.akshay.dcrs.dto.request.complaint.ComplaintCreateRequest;
import com.akshay.dcrs.dto.response.complaint.ComplaintResponse;
import com.akshay.dcrs.service.complaint.IComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final IComplaintService complaintService;

    @PostMapping
    public ComplaintResponse createComplaint(@RequestBody ComplaintCreateRequest request) {
        return complaintService.createComplaint(request);
    }
}