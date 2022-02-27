package com.olx.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.dto.Category;
import com.olx.entity.MasterDataEntity;
import com.olx.exception.InvalidCategoryIdException;
import com.olx.repo.MasterDataRepository;
import com.zensar.dto.Stock;
import com.zensar.entity.StockEntity;
import com.zensar.exception.InvalidStockIdException;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	MasterDataRepository masterDataRepository;

	@Override
	public List<Category> getAllCategories() {
		List<MasterDataEntity> stockEntities = masterDataRepository.findAll();
		List<Category> stockDtoList = new ArrayList<Category>();
		for (MasterDataEntity stockEntity : stockEntities) {

			Category stock = convertCategoryEntityIntoCategoryDTO(stockEntity);
			stockDtoList.add(stock);
		}
		return stockDtoList;
	}

	@Override
	public Category getCategoryById(long categoryId) {
		Optional<MasterDataEntity> opStockEntity = masterDataRepository.findById(categoryId);
		if (opStockEntity.isPresent()) {
			MasterDataEntity stockEntity = opStockEntity.get();
			Category stock = convertCategoryEntityIntoCategoryDTO(stockEntity);
			return stock;
		}
		throw new InvalidCategoryIdException(""+ categoryId);
	}
	
	private Category convertCategoryEntityIntoCategoryDTO(MasterDataEntity masterDataEntity) {
		Category category = this.modelMapper.map(masterDataEntity, Category.class);
		return category;
	}

}
