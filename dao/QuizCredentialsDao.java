package  hello.dao;

import hello.QuizCredentials;
import hello.RegisteredUsers;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Repository
public interface QuizCredentialsDao extends  CrudRepository<QuizCredentials , Integer>{
    public List<QuizCredentials> findAll();

    public QuizCredentials findByQuizId(Integer quizId);
   // public List<QuizCredentials> findByUserid(RegisteredUsers userId);
    
    
}