package monitoringWebsite.monitoringWebsite.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckResponse {
    private String url;            // URL, который был проверен
    private boolean isAvailable;   // Доступность сайта
    private long responseTime;     // Время отклика в миллисекундах (если сайт доступен)
    private String message;
}
