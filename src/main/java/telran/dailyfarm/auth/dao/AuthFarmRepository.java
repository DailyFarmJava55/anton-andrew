package telran.dailyfarm.auth.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.dailyfarm.auth.model.Farm;

public interface AuthFarmRepository extends MongoRepository<Farm, String> {

}
