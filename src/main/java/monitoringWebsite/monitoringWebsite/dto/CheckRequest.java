package monitoringWebsite.monitoringWebsite.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckRequest {
    private String url;       // URL для проверки
    private int timeout;      // Время ожидания отклика (в миллисекундах)
    private int retries;      // Количество попыток в случае ошибки подключения
}
