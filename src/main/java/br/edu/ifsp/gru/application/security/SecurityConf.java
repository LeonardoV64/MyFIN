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
<<<<<<< HEAD:src/main/java/com/example/application/security/SecurityConf.java
		return new InMemoryUserDetailsManager(User.withUsername("user")
				.password("{noop}123")
=======
		return new InMemoryUserDetailsManager(User.withUsername("usuario")
				.password("{noop}senha")
>>>>>>> baf4f2245e3c5ab5f9816e97b5d73e304a47ad79:src/main/java/br/edu/ifsp/gru/application/security/SecurityConf.java
				.roles("USER")
				.build());
	}

}
