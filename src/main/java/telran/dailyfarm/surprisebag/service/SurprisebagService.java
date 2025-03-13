package telran.dailyfarm.surprisebag.service;

import java.security.Principal;

import telran.dailyfarm.surprisebag.dto.SurprisebagDto;

public interface SurprisebagService {
  SurprisebagDto addQuantity(Principal principal);

  SurprisebagDto setQuantity(Principal principal, int quantity);

  SurprisebagDto removeQuantity(Principal principal);

  SurprisebagDto updateBag(Principal principal, SurprisebagDto surprisebagDto);
}
