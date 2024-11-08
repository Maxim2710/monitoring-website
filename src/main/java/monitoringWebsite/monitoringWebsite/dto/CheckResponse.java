package monitoringWebsite.monitoringWebsite.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckResponse {
    private String url;
    private boolean isAvailable;
    private long responseTime;
    private String message;
}
