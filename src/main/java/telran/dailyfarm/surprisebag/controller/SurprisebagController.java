package telran.dailyfarm.surprisebag.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.surprisebag.dto.SurprisebagDto;
import telran.dailyfarm.surprisebag.service.SurprisebagService;

@RestController
@RequestMapping("/api/farm/bag/")
@RequiredArgsConstructor
public class SurprisebagController {
  final SurprisebagService surprisebagService;

  @GetMapping("add")
  public SurprisebagDto addQuantity(Principal principal) {
    return surprisebagService.addQuantity(principal);
  }

  @PostMapping("set/{quantity}")
  public SurprisebagDto setQuantity(Principal principal, @PathVariable int quantity) {
    return surprisebagService.setQuantity(principal, quantity);
  }

  @GetMapping("remove")
  public SurprisebagDto removeQuantity(Principal principal) {
    return surprisebagService.removeQuantity(principal);
  }

  @PostMapping("update")
  public SurprisebagDto updateBag(Principal principal, @RequestBody SurprisebagDto surprisebagDto) {
    return surprisebagService.updateBag(principal, surprisebagDto);
  }
}
