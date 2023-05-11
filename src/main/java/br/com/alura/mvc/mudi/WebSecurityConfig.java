package br.com.alura.mvc.mudi;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
	
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        .antMatchers("/home/**")
        .permitAll()
        .antMatchers("/login")
        .denyAll()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin(form -> form
        		.loginPage("/login")
        		.defaultSuccessUrl("/usuario/pedido", true)
        		.permitAll()
        		).logout(logout -> {logout.logoutUrl("/logout").logoutSuccessUrl("/home");
        			}).csrf().disable();
        
		return http.build();
        
    }
	
	@Bean
	public UserDetailsManager  users(DataSource dataSource) throws Exception {
//		String idForEncode = "bcrypt";
//		Map<String, PasswordEncoder> encoders = new HashMap<>();
//		encoders.put(idForEncode, new BCryptPasswordEncoder());
//		PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idForEncode, encoders);
//		
//		UserDetails user = User.builder().username("admin").password(passwordEncoder.encode("admin")).roles("ADM").build();
		
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        
        return users;
	}
	
	
}
