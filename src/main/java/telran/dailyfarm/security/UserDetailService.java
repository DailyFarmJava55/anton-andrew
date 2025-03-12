package telran.dailyfarm.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.user.dao.UserRepository;
import telran.dailyfarm.user.model.UserAccount;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
  final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserAccount userAccount = userRepository.findById(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));
    return new User(username, userAccount.getPassword(),
        AuthorityUtils.createAuthorityList());
  }
}
