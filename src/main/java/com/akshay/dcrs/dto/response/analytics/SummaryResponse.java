package com.akshay.dcrs.dto.response.analytics;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SummaryResponse {

    private String name;
    private Long count;
}