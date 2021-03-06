package com.emyus.ca;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emyus.auth.CARepository;

@Service
@Transactional(rollbackOn = Exception.class)
public class CAService {

	@Autowired
	private CARepository caRepository;

	public List<CorrectAnswer> findAll() {
	    return caRepository.findAll();
	  }

	public void create(CARequest caRequest) {
		caRepository.create(caRequest);
	}

	public CorrectAnswer findById(CARequest caRequest) {
		return caRepository.findById(caRequest);
	}

	public void update(CARequest caRequest) {
		caRepository.update(caRequest);
	}

	public void delete(CARequest caRequest) {
		caRepository.delete(caRequest);
	}

	public List<CorrectAnswer> findByQuestionId(int questions_id) {
		return caRepository.findByQuestionId(questions_id);
	}
}
