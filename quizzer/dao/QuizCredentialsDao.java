package  quizzer.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import quizzer.model.QuizCredentials;
import quizzer.model.RegisteredUsers;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Repository
public interface QuizCredentialsDao extends  CrudRepository<QuizCredentials , Integer>{
    public List<QuizCredentials> findAll();

    public QuizCredentials findByQuizId(Integer quizId);
   // public List<QuizCredentials> findByUserid(RegisteredUsers userId);
    
    
}