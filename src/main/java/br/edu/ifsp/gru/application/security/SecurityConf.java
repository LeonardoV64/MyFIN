package br.edu.ifsp.gru.application.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import br.edu.ifsp.gru.application.views.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConf extends VaadinWebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		super.configure(http);
		setLoginView(http, LoginView.class);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/images/**");
		super.configure(web);
	}
	
	
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		return new InMemoryUserDetailsManager(User.withUsername("usuario")
				.password("{noop}senha")
				.roles("USER")
				.build());
	}

}
