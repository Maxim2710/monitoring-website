package monitoringWebsite.monitoringWebsite.controller;

import monitoringWebsite.monitoringWebsite.dto.CheckRequest;
import monitoringWebsite.monitoringWebsite.dto.CheckResponse;
import monitoringWebsite.monitoringWebsite.dto.MultiCheckRequest;
import monitoringWebsite.monitoringWebsite.service.WebsiteMonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/monitor")
public class WebsiteMonitoringController {

    @Autowired
    private WebsiteMonitoringService monitoringService;

    @PostMapping("/check")
    public CheckResponse checkWebsite(@RequestBody CheckRequest request) {
        return monitoringService.checkWebsiteAvailability(request);
    }

    @PostMapping("/check-multiple")
    public List<CheckResponse> checkMultipleWebsites(@RequestBody MultiCheckRequest request) throws InterruptedException {
        return monitoringService.checkMultipleWebsites(request);
    }
}
