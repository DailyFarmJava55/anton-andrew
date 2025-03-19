package telran.dailyfarm.farm.service;

import java.util.List;

import telran.dailyfarm.farm.dto.FarmDto;
import telran.dailyfarm.farm.dto.UpdateFarmDto;

public interface FarmService {
  FarmDto updateFarm(String login, UpdateFarmDto updateFarmDto);

  FarmDto deleteAccountFarm(String login);

  List<FarmDto> findFarms();
}
