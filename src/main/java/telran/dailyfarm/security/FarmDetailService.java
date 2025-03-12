package telran.dailyfarm.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.farm.dao.FarmRepository;
import telran.dailyfarm.farm.model.FarmAccount;

@Service("farmDetailsService")
@RequiredArgsConstructor
public class FarmDetailService implements UserDetailsService {
  final FarmRepository farmRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    FarmAccount farm = farmRepository.findById(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));
    return new User(username, farm.getPassword(),
        AuthorityUtils.createAuthorityList());
  }
}
