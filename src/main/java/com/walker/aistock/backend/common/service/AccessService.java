package com.walker.aistock.backend.common.service;

import com.walker.aistock.backend.common.entity.Ip;
import com.walker.aistock.backend.common.repository.IpRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccessService {

    IpRepository ipRepository;

    @Transactional(rollbackFor = Exception.class)
    public void checkIp(String ip) {

        Ip findedIp = ipRepository.findByIp(ip);

        if (findedIp == null) {
            ipRepository.save(Ip.create(ip));
        } else {
            findedIp.increaseCount();
        }

    }

}