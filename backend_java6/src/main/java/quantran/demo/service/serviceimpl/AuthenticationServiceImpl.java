package quantran.demo.service.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import quantran.demo.dao.AccountDao;
import quantran.demo.entity.Account;
import quantran.demo.security.auth.AuthenticationRequest;
import quantran.demo.security.auth.AuthenticationResponse;
import quantran.demo.security.auth.RegisterRequest;
import quantran.demo.service.AuthenticationService;
import quantran.demo.utility.JwtUtils;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AccountDao accountDao;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    private final AuthenticationManager authManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        Account account = Account.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .email(request.getEmail())
                .photo(request.getPhoto()).build();
        accountDao.save(account);
        String jwtToken = jwtUtils.generateToken(account);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        //compare password and password within request
        //param: UsernamePasswordAuthenticationToken implement Authentication
        //this method is implemented by ProviderManager
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        Account account = accountDao.
                findById(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        String jwtToken = jwtUtils.generateToken(account);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
