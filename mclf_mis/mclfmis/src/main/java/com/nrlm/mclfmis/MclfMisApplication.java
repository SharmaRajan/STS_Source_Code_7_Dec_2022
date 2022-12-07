package com.nrlm.mclfmis;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)  
/* @ComponentScan("com.nrlm.mclfmis.usermanagement") */
public class MclfMisApplication {

	public static void main(String[] args) {
		SpringApplication.run(MclfMisApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	@LoadBalanced
	@Qualifier("internalRestTemplate")
	RestTemplate restTemplate() {
		return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
	}

	@Bean
	@Qualifier("externalRestTemplate")
	RestTemplate externalRestTemplate() {
		return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
	}
	@Bean(name = "messageSource")
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setCacheSeconds(5);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

//	@Bean(name = "validator")
//	public LocalValidatorFactoryBean getValidator(MessageSource messageSource)  {
//		LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
//		validatorFactoryBean.setValidationMessageSource(messageSource);
//		return validatorFactoryBean;
//	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		// configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080",""));
		configuration.setAllowedOrigins(
				Arrays.asList("http://localhost:4200/", "http://localhost:8589/", "https://stgmclf-mis.dhwaniris.in","https://demomclf.dhwaniris.in/",
						"https://mclf.dhwaniris.in/","https://prodmclf.dhwaniris.in/"));
		// configuration.setAllowedOrigins(Arrays.asList());
		// configuration.setAllowedMethods(Arrays.asList("GET","PUT","POST","DELETE","OPTIONS"));
		configuration.setAllowedMethods(Arrays.asList("*")); // We use asterik, so that all HTTP methods are allowed.
		// If we want to allow credentials for HTTPResponse and credentials, here, are
		// cookies and Authorization header.
		// Or, it could be SSL client certificate.If we want this info to be included,
		// then credentials set to true.
		configuration.setAllowCredentials(true);
		// configuration.setAllowedHeaders(Arrays.asList("Authorization","Cache-Control","Content-Type"));
		configuration.setAllowedHeaders(Arrays.asList("*"));// Allowed all headers.
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		// source.registerCorsConfiguration("/authenticate", configuration);
		source.registerCorsConfiguration("/**", configuration); // Specify the path pattern.
		return (CorsConfigurationSource) source;
	}

	// @Bean
	// public WebMvcConfigurer corsConfigurer() {
	// return new WebMvcConfigurer() {
	// @Override
	// public void addCorsMappings(CorsRegistry registry) {
	// registry.addMapping("/**").allowedOrigins("http://localhost:4200/");
	// registry.addMapping("/**").allowedOrigins("http://localhost:8589/");
	// registry.addMapping("/**").allowedOrigins("http://localhost:8080/");
	// registry.addMapping("/**").allowedOrigins("http://localhost:8000/");
	// registry.addMapping("/**").allowedHeaders("*").exposedHeaders("Access-Control-Allow-Origin",
	// "Access-Control-Allow-Credentials")
	// .allowCredentials(true).maxAge(3600);
	// }
	// };
	// }

}
