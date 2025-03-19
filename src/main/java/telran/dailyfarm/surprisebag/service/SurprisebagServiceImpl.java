package telran.dailyfarm.surprisebag.service;

import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.farm.dao.FarmRepository;
import telran.dailyfarm.surprisebag.dao.SurprisebagRepository;
import telran.dailyfarm.surprisebag.dto.SurprisebagDto;
import telran.dailyfarm.surprisebag.model.Surprisebag;

@Service
@RequiredArgsConstructor
public class SurprisebagServiceImpl implements SurprisebagService {
  final FarmRepository farmRepository;
  final ModelMapper modelMapper;
  final SurprisebagRepository surprisebagRepository;

  @Override
  public SurprisebagDto addSurpriseBag(Principal principal) {
    if (!surprisebagRepository.existsById(principal.getName())) {
      Surprisebag bag = new Surprisebag();
      bag.setFarmId(principal.getName());
      surprisebagRepository.save(bag);
      return modelMapper.map(bag, SurprisebagDto.class);
    } else {
      throw new RuntimeException();
    }
  }

  @Override
  public SurprisebagDto updateBag(Principal principal, SurprisebagDto surprisebagDto) {
    Surprisebag bag = surprisebagRepository.findById(principal.getName()).orElseThrow(RuntimeException::new);
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
    surprisebagRepository.delete(bag);
    return modelMapper.map(bag, SurprisebagDto.class);
  }

}
