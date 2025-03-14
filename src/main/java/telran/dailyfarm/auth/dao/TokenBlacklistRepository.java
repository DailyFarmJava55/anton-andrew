package telran.dailyfarm.auth.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.dailyfarm.auth.model.TokenBlackList;

public interface TokenBlacklistRepository extends MongoRepository<TokenBlackList, String> {
  boolean existsByToken(String token);
}
