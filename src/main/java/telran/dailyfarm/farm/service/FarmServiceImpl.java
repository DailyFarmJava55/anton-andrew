package telran.dailyfarm.farm.service;

import org.modelmapper.ModelMapper;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.auth.dto.exceptions.UserNotFoundException;
import telran.dailyfarm.farm.dao.FarmRepository;
import telran.dailyfarm.farm.dto.FarmDto;
import telran.dailyfarm.farm.dto.UpdateFarmDto;
import telran.dailyfarm.farm.model.FarmAccount;

@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {

  final FarmRepository farmRepository;
  final ModelMapper modelMapper;

  @Override
  public FarmDto updateFarm(String login, UpdateFarmDto updateFarmDto) {
    FarmAccount farm = farmRepository.findById(login).orElseThrow(UserNotFoundException::new);
    if (updateFarmDto.getFarmName() != null) {
      farm.setFarmName(updateFarmDto.getFarmName());
    }
    farmRepository.save(farm);
    return modelMapper.map(farm, FarmDto.class);
  }

  @Override
  public FarmDto deleteAccountFarm(String login) {
    FarmAccount farm = farmRepository.findById(login).orElseThrow(UserNotFoundException::new);
    farmRepository.deleteById(login);
    return modelMapper.map(farm, FarmDto.class);
  }

}
