package com.naturemobility.api.util;

import com.naturemobility.api.config.HttpLoggingHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.channel.BootstrapHandlers;
import reactor.netty.http.client.HttpClient;

import java.nio.charset.Charset;
import java.util.Map;

@Component
public class WebClientUtil {

  private static final Logger log = LogManager.getLogger(WebClientUtil.class);

  public static <T> T WebPost(String url, Map<String, String> headers, Object params, Class<T> tClass) {
    HttpClient httpClient = HttpClient.create().wiretap(true)
      .tcpConfiguration(tcpClient ->
        tcpClient.bootstrap(bootstrap ->
          BootstrapHandlers.updateLogSupport(bootstrap, new HttpLoggingHandler())));

    WebClient webClient = WebClient
      .builder()
      .clientConnector(new ReactorClientHttpConnector(httpClient)).build();

    log.info("POST url: [" + url + "]");
    log.info("POST headers [" + headers + "]");
    log.info("POST body [" + params + "]");

    return webClient.post()
      .uri(url)
      .headers(httpHeader -> httpHeader.setAll(headers))
      .accept(MediaType.APPLICATION_JSON)
      .syncBody(params)
      .acceptCharset(Charset.forName("UTF-8"))
      .exchange()
      .block()
      .bodyToMono(tClass)
      .block();
  }

  public static <T> T WebPut(String url, Map<String, String> headers, Object param, Class<T> tClass) {
    WebClient.RequestHeadersSpec<?> request = WebClient.create(url)
      .put()
      .headers(httpHeader -> httpHeader.setAll(headers))
      .accept(MediaType.APPLICATION_JSON)
      .syncBody(param)
      .acceptCharset(Charset.forName("UTF-8"));

    log.info("PUT url: [" + url + "]");
    log.info("PUT headers [" + headers + "]");
    log.info("PUT body [" + param + "]");

    T result = request.exchange().block().bodyToMono(tClass).block();

    log.info("PUT result [" + result + "]");

    return result;
  }

  public static <T> T WebGet(String url, Map<String, String> headers, MultiValueMap<String, String> params, Class<T> tClass) {
    WebClient.RequestHeadersSpec<?> request = WebClient.create(url)
      .get()
      .uri(uriBuilder -> uriBuilder.queryParams(params).build())
      .headers(httpHeader -> httpHeader.setAll(headers))
      .accept(MediaType.APPLICATION_JSON)
      .acceptCharset(Charset.forName("UTF-8"));

    log.info("GET url: [" + url + "]");
    log.info("GET headers: [" + headers + "]");
    log.info("GET params: [" + params + "]");

    T result = request.exchange().block().bodyToMono(tClass).block();

    log.info("GET result: [" + result + "]");

    return result;
  }

  public static <T> void WebDelete(String url, Map<String, String> headers, Class<T> tClass) {
    WebClient.RequestHeadersSpec<?> request = WebClient.create(url)
      .delete()
      .headers(httpHeader -> httpHeader.setAll(headers))
      .accept(MediaType.APPLICATION_JSON)
      .acceptCharset(Charset.forName("UTF-8"));

    request.exchange().block().bodyToMono(tClass).block();
  }
}
