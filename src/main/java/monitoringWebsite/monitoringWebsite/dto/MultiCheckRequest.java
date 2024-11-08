package monitoringWebsite.monitoringWebsite.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MultiCheckRequest {
    private List<String> urls;
    private int timeout;
    private int retries;
}