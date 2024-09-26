package ex.sample.domain.sample.property;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SamplePropertyTest {

    @Autowired
    private SampleProperty sampleProperty;

    @DisplayName("SampleProperty Bean 주입 테스트")
    @Test
    void sample_property_bean_test() {
        // given
        String url = "http://localhost:8080/samples/properties" + "?key=" + "SAMPLE_API_KEY";

        // when
        String samplePropertyUrl = sampleProperty.getUrlWithKey();

        // then
        assertThat(samplePropertyUrl).isEqualTo(url);
    }
}