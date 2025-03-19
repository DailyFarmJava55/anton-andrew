package telran.dailyfarm.farm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.farm.dto.FarmDto;
import telran.dailyfarm.farm.dto.UpdateFarmDto;
import telran.dailyfarm.farm.service.FarmService;

@RestController
@RequestMapping("/api/farm/")
@RequiredArgsConstructor
public class FarmController {
  final FarmService farmService;

  @PostMapping("{login}")
  public FarmDto updateFarm(@PathVariable String login, @RequestBody UpdateFarmDto updateFarmDto) {
    return farmService.updateFarm(login, updateFarmDto);
  }

  @DeleteMapping("{login}")
  public FarmDto deleteAccountFarm(@PathVariable String login) {
    return farmService.deleteAccountFarm(login);
  }

  @GetMapping("farms")
  public List<FarmDto> findFarms() {
    return farmService.findFarms();
  }
}
