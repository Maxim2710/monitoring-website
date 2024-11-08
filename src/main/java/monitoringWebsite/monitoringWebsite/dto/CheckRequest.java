package monitoringWebsite.monitoringWebsite.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckRequest {
    private String url;
    private int timeout;
    private int retries;
}
