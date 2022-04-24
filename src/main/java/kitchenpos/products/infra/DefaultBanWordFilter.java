package kitchenpos.products.infra;

import kitchenpos.products.domain.tobe.BanWordFilter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class DefaultBanWordFilter implements BanWordFilter {
    private final RestTemplate restTemplate;

    public DefaultBanWordFilter() {
        this.restTemplate = new RestTemplateBuilder().build();
    }

    public DefaultBanWordFilter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean containsProfanity(final String text) {
        final URI url = UriComponentsBuilder.fromUriString("https://www.purgomalum.com/service/containsprofanity")
                .queryParam("text", text)
                .build()
                .toUri();
        return Boolean.parseBoolean(restTemplate.getForObject(url, String.class));
    }
}
