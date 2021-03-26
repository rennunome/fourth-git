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
		CorrectAnswer ca = new CorrectAnswer();
		ca.setAnswer(caRequest.getAnswer());
		ca.setQuestionsId(caRequest.getQuestionsId());
		caRepository.create();
	}
}
