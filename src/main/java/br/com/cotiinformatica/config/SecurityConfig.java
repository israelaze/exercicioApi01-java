// Classe que faz a segurança da API

package br.com.cotiinformatica.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
<<<<<<< HEAD
=======
import org.springframework.web.servlet.config.annotation.CorsRegistry;
>>>>>>> 411e16d64d240afed864f402495bcfec7c2de83f

import br.com.cotiinformatica.filters.JWTAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;

	// JWT
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
<<<<<<< HEAD
		
		/*	
		http.cors().and().csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().anyRequest().permitAll();
		*/
=======
>>>>>>> 411e16d64d240afed864f402495bcfec7c2de83f

		// mapear a classe JwtAuthorizationFilter (segurança da API)
		http.csrf().disable().addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
<<<<<<< HEAD
				// permitir o acesso ao console do banco de dados h2
				.antMatchers("/h2-console/**").permitAll()
=======
>>>>>>> 411e16d64d240afed864f402495bcfec7c2de83f
				// permitir o cadastro de usuário
				.antMatchers("/api/usuarios").permitAll()
				// permitir autenticação do usuário
				.antMatchers("/api/auth").permitAll()
				// permitir o envio de parâmetros adicionais no protocolo HTTP como por ex:
				// Header, Patch, et..
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated();
	}

	// configuração para liberar a documentação do SWAGGER
	private static final String[] SWAGGER = {
			// -- Swagger UI v2
			"/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
			"/configuration/security", "/swagger-ui.html", "/webjars/**",
			// -- Swagger UI v3 (OpenAPI)
			"/v3/api-docs/**", "/swagger-ui/**" };

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(SWAGGER);
	}

	// CORS
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

<<<<<<< HEAD
=======
	 
	    public void addCorsMappings(CorsRegistry corsRegistry) {
	        corsRegistry.addMapping("/**")
	                .allowedOrigins("https://angular-clientes.herokuapp.com")
	                .allowedMethods("*")
	                .maxAge(3600L)
	                .allowedHeaders("*")
	                .exposedHeaders("Authorization")
	                .allowCredentials(true);
	    }
>>>>>>> 411e16d64d240afed864f402495bcfec7c2de83f
}