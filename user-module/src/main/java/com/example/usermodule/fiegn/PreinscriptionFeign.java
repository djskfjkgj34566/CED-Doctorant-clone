package com.example.usermodule.fiegn;

import com.example.usermodule.dto.PreinscriptionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "preinscription-ms")
public interface PreinscriptionFeign {

    @PutMapping("/api/v1/ms-preinscription/")
    int update(@RequestBody PreinscriptionDto preinscription);
}