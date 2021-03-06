package com.emyus.que;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emyus.auth.QuestionRepository;
@Service
@Transactional(rollbackOn = Exception.class)
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	public List<Question> findAll() {
		return questionRepository.findAll();
	}

	public void create(QuestionRequest questionRequest) {
		questionRepository.create(questionRequest);
	}

	public void update(QuestionRequest questionRequest) {
		questionRepository.update(questionRequest);
	}

	public Question findById(QuestionRequest questionRequest) {
		return questionRepository.findById(questionRequest);
	}

	public void delete(QuestionRequest questionRequest) {
		questionRepository.delete(questionRequest);
	}

	public List<Question> findAllRandom() {
		return questionRepository.findAllRandom();
	}
}
