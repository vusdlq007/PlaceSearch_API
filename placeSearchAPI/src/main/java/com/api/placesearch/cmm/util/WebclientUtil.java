package com.api.placesearch.cmm.util;

import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


@Slf4j
@Component
public class WebclientUtil {

    public WebClient getWebClient(String endPointUrl, boolean encodeYn) throws SSLException {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(endPointUrl);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT);

        WebClient webClient = null;

        if (endPointUrl.startsWith("https://")) {
            // set insecure
            SslContext sslContext = SslContextBuilder
                    .forClient()
                    .build();

            HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));

            webClient = WebClient.builder()
                    .clientConnector(new ReactorClientHttpConnector(httpClient))
                    .baseUrl(endPointUrl)
                    .build();
        } else {
            HttpClient httpClient = HttpClient.create()
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                    .responseTimeout(Duration.ofMillis(5000))
                    .doOnConnected(conn ->
                            conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                                    .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

            // URI 암호화 여부
            if(encodeYn){
                webClient = WebClient.builder()
                        .uriBuilderFactory(factory)
                        .clientConnector(new ReactorClientHttpConnector(httpClient))
                        .baseUrl(endPointUrl)
                        .build();
            }else{
                webClient = WebClient.builder()
                        .clientConnector(new ReactorClientHttpConnector(httpClient))
                        .baseUrl(endPointUrl)
                        .build();
            }

        }

        return webClient;
    }
}
