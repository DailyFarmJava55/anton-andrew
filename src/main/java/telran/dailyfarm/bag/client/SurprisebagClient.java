package telran.dailyfarm.bag.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import telran.dailyfarm.bag.dto.SurprisebagDto;

@FeignClient(name = "DailyFarmSurprisebag", url = "${DailyFarmSurprisebag.url}")
public interface SurprisebagClient {
  @GetMapping("/api/farm/bag/add")
  public SurprisebagDto addSurpriseBag(@RequestHeader("X-User") String user);

  @PostMapping("/api/farm/bag/update")
  public SurprisebagDto updateBag(@RequestHeader("X-User") String user, @RequestBody SurprisebagDto surprisebagDto);

  @DeleteMapping("/api/farm/bag/remove")
  public SurprisebagDto deleteBag(@RequestHeader("X-User") String user);

  @GetMapping("/api/farm/bag/get/{id}")
  public SurprisebagDto getBag(@PathVariable String id);
}
