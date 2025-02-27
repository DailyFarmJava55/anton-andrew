package telran.dailyfarm.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.auth.dao.AuthUserRepository;
import telran.dailyfarm.auth.model.UserAccount;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
  final AuthUserRepository authUserRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserAccount userAccount = authUserRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException(username));
    return new User(username, userAccount.getPassword(),
        AuthorityUtils.createAuthorityList());
  }
}
