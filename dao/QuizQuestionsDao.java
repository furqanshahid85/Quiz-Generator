package  hello.dao;


import hello.QuizQuestions;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Repository
public interface QuizQuestionsDao extends  CrudRepository<QuizQuestions, Integer>{
    public List<QuizQuestions> findAll();
    public List<QuizQuestions> findByQuizId(Integer quizid);
    
}