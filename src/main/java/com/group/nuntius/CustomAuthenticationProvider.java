package com.group.nuntius;

public class CustomAuthenticationProvider {

}

/*
import com.group.nuntius.model.Client;
import com.group.nuntius.service.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        Optional<Client> clientOptional = clientRepository.getByEmail(email);
        if (!clientOptional.isPresent()) {
            Client client = new Client(email, password);
            clientRepository.save(client);
        }

        return new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
*/