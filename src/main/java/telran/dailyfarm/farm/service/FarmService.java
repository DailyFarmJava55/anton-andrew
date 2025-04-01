package telran.dailyfarm.farm.service;

import java.util.List;

import telran.dailyfarm.farm.dto.FarmDto;
import telran.dailyfarm.farm.dto.UpdateFarmDto;
import telran.dailyfarm.farm.model.FarmAccount;

public interface FarmService {
  FarmDto updateFarm(String login, UpdateFarmDto updateFarmDto);

  FarmDto deleteAccountFarm(String login);

  List<FarmDto> findFarms();

  FarmAccount findFarm(String id);
}
