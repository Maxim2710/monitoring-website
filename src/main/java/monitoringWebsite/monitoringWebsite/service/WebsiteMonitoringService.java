package monitoringWebsite.monitoringWebsite.service;


import monitoringWebsite.monitoringWebsite.dto.CheckRequest;
import monitoringWebsite.monitoringWebsite.dto.CheckResponse;
import monitoringWebsite.monitoringWebsite.dto.MultiCheckRequest;
import monitoringWebsite.monitoringWebsite.util.UrlValidator;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class WebsiteMonitoringService {

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public CheckResponse checkWebsiteAvailability(CheckRequest request) {
        CheckResponse response = new CheckResponse();
        response.setUrl(request.getUrl());

        if (!UrlValidator.isValidUrl(request.getUrl())) {
            response.setAvailable(false);
            response.setMessage("Invalid URL format.");
            return response;
        }

        RestTemplate restTemplate = new RestTemplate();
        long startTime = System.currentTimeMillis();

        for (int attempt = 1; attempt <= request.getRetries(); attempt++) {
            try {
                restTemplate.getForObject(request.getUrl(), String.class);
                long responseTime = System.currentTimeMillis() - startTime;

                response.setAvailable(true);
                response.setResponseTime(responseTime);
                response.setMessage("Website is available.");
                return response;
            } catch (ResourceAccessException e) {
                if (attempt == request.getRetries()) {
                    response.setAvailable(false);
                    response.setMessage("Failed to connect after " + attempt + " attempts.");
                }
            }
        }

        return response;
    }

    public List<CheckResponse> checkMultipleWebsites(MultiCheckRequest request) throws InterruptedException {
        List<CheckResponse> responses = new ArrayList<>();
        List<Future<CheckResponse>> futures = new ArrayList<>();

        for (String url : request.getUrls()) {
            futures.add(executorService.submit(() -> {
                CheckRequest checkRequest = new CheckRequest();
                checkRequest.setUrl(url);
                checkRequest.setTimeout(request.getTimeout());
                checkRequest.setRetries(request.getRetries());
                return checkWebsiteAvailability(checkRequest);
            }));
        }

        for (Future<CheckResponse> future : futures) {
            try {
                responses.add(future.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        return responses;
    }
}