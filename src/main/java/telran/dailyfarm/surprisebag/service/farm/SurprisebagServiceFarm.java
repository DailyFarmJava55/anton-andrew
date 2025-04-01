package telran.dailyfarm.surprisebag.service.farm;

import java.security.Principal;

import telran.dailyfarm.surprisebag.dto.SurprisebagDto;

public interface SurprisebagServiceFarm {

  SurprisebagDto addSurpriseBag(Principal principal);

  SurprisebagDto getSurpriseBag(String id);

  SurprisebagDto updateBag(Principal principal, SurprisebagDto surprisebagDto);

  SurprisebagDto deleteBag(Principal principal);
}
