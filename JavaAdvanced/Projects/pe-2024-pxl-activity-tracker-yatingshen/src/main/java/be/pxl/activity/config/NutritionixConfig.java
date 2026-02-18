package be.pxl.activity.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration  // Ensure this annotation is present
public class NutritionixConfig {

    @Value("${nutritionix.app.id}")
    private String appId;

    @Value("${nutritionix.app.key}")
    private String appKey;

    @Value("${nutritionix.api.url}")
    private String apiUrl;

    public String getAppId() {
        return appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}
