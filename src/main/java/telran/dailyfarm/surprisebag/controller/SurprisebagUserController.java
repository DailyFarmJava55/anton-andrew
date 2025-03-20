package telran.dailyfarm.surprisebag.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.surprisebag.service.SurprisebagService;

@RestController
@RequestMapping("/api/user/bag/")
@RequiredArgsConstructor
public class SurprisebagUserController {
  final SurprisebagService surprisebagService;

}
