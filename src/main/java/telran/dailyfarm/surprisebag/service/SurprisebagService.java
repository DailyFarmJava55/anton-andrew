package telran.dailyfarm.surprisebag.service;

import java.security.Principal;

import telran.dailyfarm.surprisebag.dto.SurprisebagDto;

public interface SurprisebagService {

  SurprisebagDto addSurpriseBag(Principal principal);

  SurprisebagDto getSurpriseBag(String id);

  SurprisebagDto updateBag(Principal principal, SurprisebagDto surprisebagDto);

  SurprisebagDto deleteBag(Principal principal);
}
