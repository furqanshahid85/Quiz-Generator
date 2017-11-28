package  hello.dao;

import hello.QuizCredentials;
import hello.StudentScore;
import hello.RegisteredUsers;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Repository
public interface StudentScoreDao extends  CrudRepository<StudentScore , Integer>{
    public List<StudentScore> findAll();

    public StudentScore findByResultId(Integer resultId);
   // public List<StudentScore> findByStudentId(RegisteredUsers studentId);
   // public List<StudentScore> findByQuizId(QuizCredentials quizId);
    
}