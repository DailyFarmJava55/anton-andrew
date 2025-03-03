package telran.dailyfarm.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.auth.dao.AuthFarmRepository;
import telran.dailyfarm.auth.model.Farm;

@Service("FarmDetailsService")
@RequiredArgsConstructor
public class FarmDetailServiceImpl implements UserDetailsService {
  final AuthFarmRepository authFarmRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Farm farm = authFarmRepository.findById(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));
    return new User(username, farm.getPassword(),
        AuthorityUtils.createAuthorityList());
  }
}
