package com.coderscampus.assignment13.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderscampus.assignment13.domain.Account;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	Optional<Account> FindByID(Long accountID);

}
