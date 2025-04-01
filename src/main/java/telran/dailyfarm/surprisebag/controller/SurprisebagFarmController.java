package telran.dailyfarm.surprisebag.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.surprisebag.dto.SurprisebagDto;
import telran.dailyfarm.surprisebag.service.farm.SurprisebagServiceFarm;

@RestController
@RequestMapping("/api/farm/bag/")
@RequiredArgsConstructor
public class SurprisebagFarmController {
  final SurprisebagServiceFarm surprisebagServiceFarm;

  @GetMapping("add")
  public SurprisebagDto addSurprisebag(Principal principal) {
    return surprisebagServiceFarm.addSurpriseBag(principal);
  }

  @PostMapping("update")
  public SurprisebagDto updateBag(Principal principal, @RequestBody SurprisebagDto surprisebagDto) {
    return surprisebagServiceFarm.updateBag(principal, surprisebagDto);
  }

  @DeleteMapping("remove")
  public SurprisebagDto deleteBag(Principal principal) {
    return surprisebagServiceFarm.deleteBag(principal);
  }

  @GetMapping("get/{id}")
  public SurprisebagDto getBag(@PathVariable String id) {
    return surprisebagServiceFarm.getSurpriseBag(id);
  }
}
