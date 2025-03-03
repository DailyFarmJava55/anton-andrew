package telran.dailyfarm.farm.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.dailyfarm.farm.model.FarmAccount;

public interface FarmRepository extends MongoRepository<FarmAccount, String> {

}
