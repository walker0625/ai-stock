package com.walker.aistock.backend.common.repository;

import com.walker.aistock.backend.common.entity.Ip;
import com.walker.aistock.backend.common.entity.Principle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpRepository extends JpaRepository<Ip, Long> {

    Ip findByIp(String ip);

}

