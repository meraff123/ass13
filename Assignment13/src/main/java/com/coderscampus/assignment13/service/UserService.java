package com.coderscampus.assignment13.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.Address;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.AccountRepository;
import com.coderscampus.assignment13.repository.AddressRepository;
import com.coderscampus.assignment13.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private AddressRepository addressRepo;

	public List<User> findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	public List<User> findByNameAndUsername(String name, String username) {
		return userRepo.findByNameAndUsername(name, username);
	}

	public List<User> findByCreatedDateBetween(LocalDate date1, LocalDate date2) {
		return userRepo.findByCreatedDateBetween(date1, date2);
	}

	public User findExactlyOneUserByUsername(String username) {
		List<User> users = userRepo.findExactlyOneUserByUsername(username);
		if (!users.isEmpty())
			return users.get(0);
		else
			return new User();
	}

	public Set<User> findAll() {
		return userRepo.findAllUsersWithAccountsAndAddresses();
	}

	public Optional<User> findById(Long userId) {
		return userRepo.findById(userId);
	}

	public User saveUser(User user) {
		if (user.getAddress() == null) {
			Address address = new Address();
			user.setAddress(address);
			address.setUser(user);
			addressRepo.save(address);
		} else if (user.getAddress().getUser() == null) {
			user.getAddress().setUser(user);
		}
		return userRepo.save(user);
	}

	public void delete(Long userId) {
		Optional<User> user = userRepo.findById(userId);
		user.ifPresent(userRepo::delete);
	}

	public Account findAccountById(Long accountId) {
		Optional<Account> account = accountRepo.findById(accountId);
		return account.orElse(new Account());
	}

	public Account saveAccount(Account account) {
		return accountRepo.save(account);
	}
}
