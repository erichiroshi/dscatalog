package com.erichiroshi.msoauth.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.erichiroshi.msoauth.entities.User;

@Component
@FeignClient(name = "ms-user", path = "/users")
public interface UserFeignClient {

	@GetMapping("/searchUser")
	ResponseEntity<User> findByEmailUser(@RequestParam String email);
}
