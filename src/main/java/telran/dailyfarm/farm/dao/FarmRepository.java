package telran.dailyfarm.farm.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import telran.dailyfarm.farm.model.FarmAccount;

public interface FarmRepository extends MongoRepository<FarmAccount, String> {
  @Query("{ '_id': { $in: ?0 } }")
  List<FarmAccount> findFarmsByIds(Set<String> farmIds);
}
