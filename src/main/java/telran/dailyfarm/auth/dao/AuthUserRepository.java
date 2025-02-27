package telran.dailyfarm.auth.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.dailyfarm.auth.model.UserAccount;

public interface AuthUserRepository extends MongoRepository<UserAccount, String> {

}
