package com.olx.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.entity.MasterDataEntity;

public interface MasterDataRepository extends JpaRepository<MasterDataEntity, Integer>{

	Optional<MasterDataEntity> findById(long categoryId);

}
