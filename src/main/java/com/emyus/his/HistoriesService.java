package com.emyus.his;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emyus.auth.HistoriesRepository;

@Service
@Transactional(rollbackOn = Exception.class)
public class HistoriesService {

	@Autowired
	private HistoriesRepository historiesRepository;

	public List<Histories> findAll() {
	    return historiesRepository.findAll();
	  }

	public void create(HistoriesRequest historiesRequest) {
		historiesRepository.create(historiesRequest);
	}
}
