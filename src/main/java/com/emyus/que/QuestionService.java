package com.emyus.que;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emyus.auth.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Transactional
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Transactional
    public Question findOne(int id) {
        return questionRepository.findOne(id);
    }

    @Transactional
    public void save(Question question) {
    	questionRepository.save(question);
    }

    @Transactional
    public void update(Question question) {
    	questionRepository.update(question);
    }

    @Transactional
    public void delete(int id) {
    	questionRepository.delete(id);
    }
}
