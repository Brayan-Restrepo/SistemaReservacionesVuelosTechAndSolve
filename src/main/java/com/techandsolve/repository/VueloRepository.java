package com.techandsolve.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.techandsolve.model.entity.Vuelo;

@Repository
public interface VueloRepository extends PagingAndSortingRepository<Vuelo, Long>  {

}
