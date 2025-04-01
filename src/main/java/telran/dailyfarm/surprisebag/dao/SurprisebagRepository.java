package telran.dailyfarm.surprisebag.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import telran.dailyfarm.surprisebag.model.Surprisebag;

public interface SurprisebagRepository extends MongoRepository<Surprisebag, String> {
  @Query("{ '_id': ?0 }")
  void updateQuantityById(String id, Integer newQuantity);
}
