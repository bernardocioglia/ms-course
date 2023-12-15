package com.devsuperior.hrapigatewayzuul.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private JwtTokenStore tokenStore;

	private static final String[] PUBLIC = { "/hr-oauth/oauth/token" };

	private static final String[] OPERATOR = { "/hr-worker/**" };

	// @formatter:off
	private static final String[] ADMIN = {
			"/hr-payroll/**"
			, "/hr-user/**"
			, "/actuator/**"
			, "/hr-worker/actuator/**"
			, "/hr-oauth/actuator/**"
			};
	// @formatter:on

	@Override
	public void configure(final ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(this.tokenStore);
	}

	@Override
	// @formatter:off
	public void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(ResourceServerConfig.PUBLIC)
		.permitAll()
		.antMatchers(HttpMethod.GET, ResourceServerConfig.OPERATOR)
		.hasAnyRole("OPERATOR", "ADMIN")
		.antMatchers(ResourceServerConfig.ADMIN)
		.hasRole("ADMIN")
		.anyRequest()
		.authenticated();

		http.cors().configurationSource(this.corsConfigurationSource());
	}
	// @formatter:on

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
		corsConfiguration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "PATCH"));
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		final FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
				new CorsFilter(this.corsConfigurationSource()));

		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}
