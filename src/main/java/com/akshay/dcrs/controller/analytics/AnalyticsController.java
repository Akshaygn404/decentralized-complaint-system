package com.akshay.dcrs.controller.analytics;

import com.akshay.dcrs.dto.response.analytics.SummaryResponse;
import com.akshay.dcrs.service.analytics.IAnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

@RestController
@RequestMapping("/api/v1/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final IAnalyticsService analyticsService;



    @PreAuthorize("hasRole('OFFICER') or hasRole('SUPER_ADMIN')")
    @GetMapping("/department-summary")
    public List<SummaryResponse> getDepartmentSummary() {
        return analyticsService.getDepartmentSummary();
    }

    @PreAuthorize("hasRole('OFFICER') or hasRole('SUPER_ADMIN')")
    @GetMapping("/ward-summary")
    public List<SummaryResponse> getWardSummary() {
        return analyticsService.getWardSummary();
    }

    @PreAuthorize("hasRole('OFFICER') or hasRole('SUPER_ADMIN')")
    @GetMapping("/escalation-rate")
    public Double getEscalationRate() {
        return analyticsService.getEscalationRate();
    }

    @PreAuthorize("hasRole('OFFICER') or hasRole('SUPER_ADMIN')")
    @GetMapping("/avg-resolution-time")
    public Double getAverageResolutionTime() {
        return analyticsService.getAverageResolutionTime();
    }
}