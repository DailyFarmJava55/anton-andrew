package telran.dailyfarm.surprisebag.service.farm;

import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.auth.dto.exceptions.UserNotFoundException;
import telran.dailyfarm.farm.dao.FarmRepository;
import telran.dailyfarm.farm.model.FarmAccount;
import telran.dailyfarm.surprisebag.dao.SurprisebagRepository;
import telran.dailyfarm.surprisebag.dto.SurprisebagDto;
import telran.dailyfarm.surprisebag.model.Surprisebag;

@Service
@RequiredArgsConstructor
public class SurprisebagServiceFarmImpl implements SurprisebagServiceFarm {
  final FarmRepository farmRepository;
  final ModelMapper modelMapper;
  final SurprisebagRepository surprisebagRepository;

  @Override
  public SurprisebagDto addSurpriseBag(Principal principal) {
    if (!surprisebagRepository.existsById(principal.getName())) {
      Surprisebag bag = new Surprisebag();
      bag.setFarmId(principal.getName());
      surprisebagRepository.save(bag);
      farmRepository.findById(principal.getName()).get().setSurprisebag(bag);
      return modelMapper.map(bag, SurprisebagDto.class);
    } else {
      throw new RuntimeException("Surprisebag not exist");
    }
  }

  @Override
  public SurprisebagDto updateBag(Principal principal, SurprisebagDto surprisebagDto) {
    Surprisebag bag = surprisebagRepository.findById(principal.getName()).orElseThrow(RuntimeException::new);
    FarmAccount farm = farmRepository.findById(principal.getName()).orElseThrow(UserNotFoundException::new);
    String name = surprisebagDto.getName();
    if (name != null) {
      bag.setName(name);
    }
    String description = surprisebagDto.getDescription();
    if (description != null) {
      bag.setDescription(description);
    }
    Double price = surprisebagDto.getPrice();
    if (price != null) {
      bag.setPrice(price);
    }
    Integer quantity = surprisebagDto.getQuantity();
    if (quantity != null) {
      bag.setQuantity(quantity);
    }
    surprisebagRepository.save(bag);
    farm.setSurprisebag(bag);
    farmRepository.save(farm);
    return modelMapper.map(bag, SurprisebagDto.class);
  }

  @Override
  public SurprisebagDto getSurpriseBag(String id) {
    Surprisebag bag = surprisebagRepository.findById(id).orElseThrow(RuntimeException::new);
    return modelMapper.map(bag, SurprisebagDto.class);
  }

  @Override
  public SurprisebagDto deleteBag(Principal principal) {
    Surprisebag bag = surprisebagRepository.findById(principal.getName()).orElseThrow(RuntimeException::new);
    FarmAccount farmAccount = farmRepository.findById(principal.getName()).orElseThrow(UserNotFoundException::new);
    surprisebagRepository.delete(bag);
    farmAccount.setSurprisebag(new Surprisebag());
    farmRepository.save(farmAccount);
    return modelMapper.map(bag, SurprisebagDto.class);
  }

}
