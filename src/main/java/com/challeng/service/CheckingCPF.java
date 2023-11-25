package com.challeng.service;

import com.challeng.dto.StatusCpfDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${api.mock.url}", name = "cpfCheck")
@Component
public interface CheckingCPF {

         @GetMapping("{cpf}/json")
         StatusCpfDTO  checkCpf(@PathVariable String cpf);
}
