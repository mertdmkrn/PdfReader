package pdfReadProject.app.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import pdfReadProject.app.service.KullaniciService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private KullaniciService kullaniciService;
	
	@Autowired
	AuthenticationSuccessHandler successHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(kullaniciService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
 
		http
		.authorizeRequests()
		.antMatchers("/admin/**").access("hasAnyRole('ROLE_ADMIN')")
		.antMatchers("/ogretmen/**").access("hasAnyRole('ROLE_OGRETMEN')")
		.antMatchers("/projeFiltrele").permitAll()
		.and()
		.formLogin()
		.loginPage("/giris")
		.successHandler(successHandler)
		.permitAll()	
		.and().logout();
	       
		http.csrf().disable();
    }
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}