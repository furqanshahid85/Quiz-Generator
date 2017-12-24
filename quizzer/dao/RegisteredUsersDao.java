package quizzer.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import quizzer.model.RegisteredUsers;

import java.util.List;


@Repository
@Transactional
public interface RegisteredUsersDao extends CrudRepository<RegisteredUsers, Integer> {
	
	public List<RegisteredUsers> findAll();

	public RegisteredUsers findByUserId(Integer userId);

	public List<RegisteredUsers> findByUserRole(String userRole);

	public List<RegisteredUsers> findByUserPassword(String userPassword);

	public RegisteredUsers findByUserName(String name);
}
