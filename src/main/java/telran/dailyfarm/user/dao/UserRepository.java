package telran.dailyfarm.user.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.dailyfarm.user.model.UserAccount;

public interface UserRepository extends MongoRepository<UserAccount, String> {

}
