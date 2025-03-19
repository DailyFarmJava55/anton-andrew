package telran.dailyfarm.auth.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Getter
@Document(collection = "tokenBlackList")
public class TokenBlackList {
  @Id
  private String id;
  private String token;
  private Date expiresAt;

  public TokenBlackList(Date expiresAt, String token) {
    this.expiresAt = expiresAt;
    this.token = token;
  }
}
