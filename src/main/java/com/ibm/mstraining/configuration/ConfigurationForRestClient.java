package com.ibm.mstraining.configuration;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.security.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

import feign.Logger;
import feign.Logger.Level;
import feign.RequestInterceptor;

@Configuration
public class ConfigurationForRestClient {

    @Bean(name = "xptoOAuth2ProtectedResourceDetails")
    protected ClientCredentialsResourceDetails  resource() {

    	ClientCredentialsResourceDetails  resource = new ClientCredentialsResourceDetails ();
    	resource.setId("...");
		resource.setAccessTokenUri("https://jp-tok.appid.cloud.ibm.com/oauth/v4/73123fb3-8ae4-41b5-9291-3f48dbe29777/token");
		resource.setClientId("b473668f-e3bb-41ad-bfd2-c2b3d33365ca");
		resource.setClientSecret("ZTA4ZmFmNTYtNTQ4ZC00M2Y1LTg5YzktNmExOWViZTI4NmUw");
		resource.setGrantType("client_credentials");
		resource.setScope( new ArrayList<String>() { 
            { 
                add("microservice"); 
               
            } });
		resource.setClientAuthenticationScheme(AuthenticationScheme.header);
		return resource;
    }

    @Bean(name = "xptoOauth2ClientContext")
    public OAuth2ClientContext oauth2ClientContext() {

        return new DefaultOAuth2ClientContext(new DefaultAccessTokenRequest());
    }

    

    @Bean
    protected RequestInterceptor oauth2FeignRequestInterceptor(
            @Qualifier("xptoOauth2ClientContext") OAuth2ClientContext context,
            @Qualifier("xptoOAuth2ProtectedResourceDetails") OAuth2ProtectedResourceDetails resourceDetails) {

        return new OAuth2FeignRequestInterceptor(context, resourceDetails);
    }

    @Bean
    @Primary
    public OAuth2RestTemplate oauth2RestTemplate(
            @Qualifier("xptoOauth2ClientContext") OAuth2ClientContext context,
            @Qualifier("xptoOAuth2ProtectedResourceDetails") OAuth2ProtectedResourceDetails resourceDetails) {

        OAuth2RestTemplate template = new OAuth2RestTemplate(resourceDetails,
                context);

        return template;
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Level.FULL;
    }
}