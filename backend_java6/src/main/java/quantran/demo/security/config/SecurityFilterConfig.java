package quantran.demo.security.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import quantran.demo.security.auth.JwtAuthFilter;

/*is used to indicate that the class is a configuration class in Spring.used in conjunction with other annotations to define
 beans and configuration settings.*/
@Configuration
/*is used to enable the web security configuration in a
 Spring application.web security configuration is automatically applied*/
@EnableWebSecurity
@RequiredArgsConstructor
@CrossOrigin
public class SecurityFilterConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    //session creation policy(cách chúng ta muốn xử lý session trong authencication system)
    //stateless: ko lưu session state(sau khi user dc authen và request hoàn thành,nó sẽ ko lưu)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(
                AbstractHttpConfigurer::disable
        ).authorizeHttpRequests(
                (request) -> request.
                        requestMatchers(
                                "/api/auth/**",
                                "/api/product/**",
                                "/api/category/**").permitAll().anyRequest().authenticated()
        ).sessionManagement(
                //vô hiệu hóa session
                (session) -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
        ).authenticationProvider(
                //chọn authenticationProvider(ở đây là DaoAuthenticationProvider)
                authenticationProvider
        ).addFilterBefore(
                //add Filter và thêm nó vào trước usernamepasswordAuthenticationfilter
                //các Filter này sẽ dc thực thi theo trình tự
                jwtAuthFilter, UsernamePasswordAuthenticationFilter.class
        );
        return httpSecurity.build();
    }


}
