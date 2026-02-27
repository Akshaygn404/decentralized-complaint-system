package com.akshay.dcrs.dto.request.complaint;

import lombok.Data;

@Data
public class ComplaintCreateRequest {

    private String title;
    private String description;
    private String category;
    private Double latitude;
    private Double longitude;
}