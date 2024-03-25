package com.careforyou.customerservice.customerapp.repository;

import com.careforyou.customerservice.customerapp.entities.NameInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameInfoRepository extends CrudRepository<NameInfo, Integer> {
}
