package com.nic.nrlm_aajeevika;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.CacheControl;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class NrlmAajeevikaApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(NrlmAajeevikaApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		// configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080",""));
		configuration.setAllowedOrigins(
			Arrays.asList("http://localhost:4200/", "http://localhost:8589/","https://stgaajeevika.dhwaniris.in/"));
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

//	@Configuration
//	public class AdditionalResourceWebConfiguration implements WebMvcConfigurer {
//		@Override
//		public void addResourceHandlers(final ResourceHandlerRegistry registry) {
//			registry.addResourceHandler("/images/**")
//					.addResourceLocations("file:src/main/uploads\\");
//		}
//	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		// Register resource handler for images
		registry.addResourceHandler("/images/**").addResourceLocations("/src/main/uploads/")
				.setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
	}
}
