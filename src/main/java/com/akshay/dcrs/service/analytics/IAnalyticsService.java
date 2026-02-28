package com.akshay.dcrs.service.analytics;

import com.akshay.dcrs.dto.response.analytics.SummaryResponse;

import java.util.List;

public interface IAnalyticsService {

    List<SummaryResponse> getDepartmentSummary();

    List<SummaryResponse> getWardSummary();

    Double getEscalationRate();

    Double getAverageResolutionTime();
}