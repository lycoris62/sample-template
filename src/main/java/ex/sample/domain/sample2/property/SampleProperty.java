package ex.sample.domain.sample2.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("sample2.api")
public record SampleProperty(
    String url,
    String key
) {

    public String getUrlWithKey() {
        return url + "?key=" + key;
    }
}
