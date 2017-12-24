package  quizzer.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import quizzer.model.QuizQuestions;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Repository
public interface QuizQuestionsDao extends  CrudRepository<QuizQuestions, Integer>{
    public List<QuizQuestions> findAll();
    public List<QuizQuestions> findByQuizId(Integer quizid);
    
}