package com.emyus.auth;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.emyus.his.Histories;
import com.emyus.his.HistoriesRequest;

@Mapper
public interface HistoriesRepository {

	public void create(HistoriesRequest historiesRequest);

	public List<Histories> findAll();

}
