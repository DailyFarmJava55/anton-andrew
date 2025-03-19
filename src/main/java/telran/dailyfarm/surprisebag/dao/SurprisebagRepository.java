package telran.dailyfarm.surprisebag.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.dailyfarm.surprisebag.model.Surprisebag;

public interface SurprisebagRepository extends MongoRepository<Surprisebag, String> {

}
