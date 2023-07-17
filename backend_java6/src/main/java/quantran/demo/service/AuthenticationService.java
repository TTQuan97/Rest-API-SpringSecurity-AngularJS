package quantran.demo.service;

import quantran.demo.security.auth.AuthenticationRequest;
import quantran.demo.security.auth.AuthenticationResponse;
import quantran.demo.security.auth.RegisterRequest;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
