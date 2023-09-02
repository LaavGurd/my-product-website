package org.satel.pfr.outbound.campaign.communication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class StringController {

    @PostMapping(path = "/process", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> processString(@RequestBody String inputString) {
        // You can perform any processing you want with the inputString here
        //String processedString = "Processed: " + inputString;
        log.info("inputString : {}", inputString);
        return new ResponseEntity<>(inputString, HttpStatus.OK);
    }
}


package org.satel.pfr.outbound.campaign.communication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        System.out.println("CORS configure start");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration conf = new CorsConfiguration().applyPermitDefaultValues();
        conf.addAllowedMethod(HttpMethod.GET);
        conf.addAllowedMethod(HttpMethod.PUT);
        conf.addAllowedMethod(HttpMethod.POST);
        conf.addAllowedMethod(HttpMethod.OPTIONS);
        conf.addAllowedMethod(HttpMethod.PATCH);
        conf.addAllowedMethod(HttpMethod.DELETE);
        /*conf.addAllowedHeader(HttpHeaders.LOCATION);
        conf.addAllowedHeader(HttpHeaders.AUTHORIZATION);*/
        conf.addAllowedOrigin("*");
        conf.addAllowedHeader("*");
        source.registerCorsConfiguration("/**",conf);
        System.out.println("CORS configure end");
        return new CorsFilter(source);
    }
}
