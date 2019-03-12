package br.com.library.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.library.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
	public User findByUsername(String username);
}
