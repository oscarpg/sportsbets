package com.rurallabs.sportsbets;

import javax.servlet.Filter;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SportsbetsApplication {

	@Bean
    public Filter characterEncodingFilter() {
        final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
	
	@Bean
	public ConfigurablePasswordEncryptor configurablePasswordEncryptor() {
		return new ConfigurablePasswordEncryptor();
	}
	
	@Order(Ordered.HIGHEST_PRECEDENCE)
	@Configuration
	protected static class AuthenticationSecurity extends GlobalAuthenticationConfigurerAdapter {

		@Autowired
		private AuthenticationProvider authenticationProvider;
		
		@Override
		public void init(final AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(this.authenticationProvider);
		}
	}
	
	public static void main(final String[] args) {
		SpringApplication.run(SportsbetsApplication.class, args);
	}
	
	@Bean
	public ApplicationSecurity applicationSecurity() {
		return new ApplicationSecurity();
	}
	
	@Order(Ordered.LOWEST_PRECEDENCE - 8)
	protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(final HttpSecurity http) throws Exception {
			http.authorizeRequests()
				.antMatchers("/home").permitAll()
				.antMatchers("/register").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/css/*.css").permitAll()
				.antMatchers("/js/*.js").permitAll()
				.antMatchers("/images/*.js").permitAll()
				.antMatchers("/*").fullyAuthenticated()
					.and().formLogin().loginPage("/login").failureUrl("/login?error")
					.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.and().exceptionHandling()
					.accessDeniedPage("/login?error");
		}

	}
}
