package telran.dailyfarm.order.dao;

import java.util.Map;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AggregationService {
  final MongoTemplate mongoTemplate;

  public Map<String, Object> getSurpriseBagAndFarmData(String surpriseBagId) {
    MatchOperation matchSurpriseBag = Aggregation
        .match(Criteria.where("_id").is(surpriseBagId));
    ProjectionOperation projectFields = Aggregation.project()
        .andInclude("_id", "name", "quantity", "price");
    Aggregation aggregation = Aggregation.newAggregation(matchSurpriseBag, projectFields);
    AggregationResults<Document> results = mongoTemplate.aggregate(aggregation,
        "surprisebag", Document.class);
    return results.getMappedResults().stream().findFirst().orElseThrow(
        () -> new RuntimeException("Data not found"));
  }

  public void mergeOrders(String orderId, String userId, String farmId, Integer quantity,
      boolean cancelOrder) {
    if (!cancelOrder) {
      Update update = new Update().push("orders", orderId);
      Update updateQuantity = new Update().inc("quantity", -quantity);
      mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(userId)),
          update,
          "users");
      mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(farmId)),
          update,
          "farms");
      mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(farmId)),
          updateQuantity,
          "surprisebag");
    }
    if (cancelOrder) {
      Update updateQuantity = new Update().inc("quantity", quantity);
      mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(farmId)),
          updateQuantity, "surprisebag");
    }
  }

  public Map<String, Object> getUserOrders(String userId) {
    MatchOperation matchOperation = Aggregation.match(Criteria.where("_id").is(userId));
    ProjectionOperation projectionOperation = Aggregation.project().andInclude("orders");
    Aggregation aggregation = Aggregation.newAggregation(matchOperation, projectionOperation);
    AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "users", Document.class);
    return results.getMappedResults().stream().findFirst().orElseThrow(() -> new RuntimeException("Data not found"));
  }

}
