package telran.dailyfarm.surprisebag.service;

import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.auth.dto.exceptions.UserNotFoundException;
import telran.dailyfarm.farm.dao.FarmRepository;
import telran.dailyfarm.farm.model.FarmAccount;
import telran.dailyfarm.surprisebag.dto.SurprisebagDto;
import telran.dailyfarm.surprisebag.model.Surprisebag;

@Service
@RequiredArgsConstructor
public class SurprisebagServiceImpl implements SurprisebagService {
  final FarmRepository farmRepository;
  final ModelMapper modelMapper;

  @Override
  public SurprisebagDto addQuantity(Principal principal) {
    FarmAccount farm = getFarm(principal.getName());
    if (farm.getSurprisebag() == null) {
      Surprisebag bag = new Surprisebag();
      bag.setQuantity(1);
      farm.setSurprisebag(bag);
      farmRepository.save(farm);
      return modelMapper.map(bag, SurprisebagDto.class);
    } else {
      Surprisebag bag = farmRepository.findById(principal.getName()).get().getSurprisebag();
      bag.setQuantity(bag.getQuantity() + 1);
      farm.setSurprisebag(bag);
      farmRepository.save(farm);
      return modelMapper.map(bag, SurprisebagDto.class);
    }
  }

  @Override
  public SurprisebagDto setQuantity(Principal principal, int quantity) {
    FarmAccount farm = getFarm(principal.getName());
    if (farm.getSurprisebag() == null) {
      Surprisebag bag = new Surprisebag();
      bag.setQuantity(quantity);
      farm.setSurprisebag(bag);
      farmRepository.save(farm);
      return modelMapper.map(bag, SurprisebagDto.class);
    } else {
      Surprisebag bag = farm.getSurprisebag();
      bag.setQuantity(quantity);
      farm.setSurprisebag(bag);
      farmRepository.save(farm);
      return modelMapper.map(bag, SurprisebagDto.class);
    }
  }

  @Override
  public SurprisebagDto removeQuantity(Principal principal) {
    FarmAccount farm = getFarm(principal.getName());
    if (farm.getSurprisebag() == null && farm.getSurprisebag().getQuantity() == 0) {
      throw new IllegalArgumentException("Surprise bag is out of stock or not available");
    } else {
      Surprisebag bag = farm.getSurprisebag();
      bag.setQuantity(bag.getQuantity() - 1);
      farm.setSurprisebag(bag);
      farmRepository.save(farm);
      return modelMapper.map(bag, SurprisebagDto.class);
    }
  }

  private FarmAccount getFarm(String email) {
    FarmAccount farm = farmRepository.findById(email).orElseThrow(UserNotFoundException::new);
    return farm;
  }

  @Override
  public SurprisebagDto updateBag(Principal principal, SurprisebagDto surprisebagDto) {
    FarmAccount farm = getFarm(principal.getName());
    Surprisebag bag = farm.getSurprisebag();
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
    farm.setSurprisebag(bag);
    farmRepository.save(farm);
    return modelMapper.map(bag, SurprisebagDto.class);
  }

}
