package telran.dailyfarm.auth.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.dailyfarm.auth.model.User;

public interface AuthUserRepository extends MongoRepository<User, String> {

}
