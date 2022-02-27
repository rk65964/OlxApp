package com.olx.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.entity.AdvertiseEntity;

public interface AdvertiseRepository extends JpaRepository<AdvertiseEntity, Integer>{

}
