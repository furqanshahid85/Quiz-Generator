package  quizzer.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import quizzer.model.QuizCredentials;
import quizzer.model.RegisteredUsers;
import quizzer.model.StudentScore;

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