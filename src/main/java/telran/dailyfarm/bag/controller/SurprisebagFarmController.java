package telran.dailyfarm.bag.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.bag.client.SurprisebagClient;
import telran.dailyfarm.bag.dto.SurprisebagDto;

@RestController
@RequestMapping("/api/farm/bag/")
@RequiredArgsConstructor
public class SurprisebagFarmController {
  final SurprisebagClient surprisebagClient;

  @GetMapping("add")
  public SurprisebagDto addSurprisebag(Principal principal) {
    return surprisebagClient.addSurpriseBag(principal.getName());
  }

  @PostMapping("update")
  public SurprisebagDto updateBag(Principal principal, @RequestBody SurprisebagDto surprisebagDto) {
    return surprisebagClient.updateBag(principal.getName(), surprisebagDto);
  }

  @DeleteMapping("remove")
  public SurprisebagDto deleteBag(Principal principal) {
    return surprisebagClient.deleteBag(principal.getName());
  }

  @GetMapping("get/{id}")
  public SurprisebagDto getBag(@PathVariable String id) {
    return surprisebagClient.getBag(id);
  }
}
