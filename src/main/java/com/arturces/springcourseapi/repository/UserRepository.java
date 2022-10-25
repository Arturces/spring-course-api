package com.arturces.springcourseapi.repository;


import com.arturces.springcourseapi.domain.User;
import com.arturces.springcourseapi.domain.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    public Optional<User> login(String email, String password);

    @Transactional(readOnly = false) //annotation para leitura e escrita
    @Modifying//annotation que atualiza o role
    @Query("UPDATE User SET role = ?2 WHERE  id = ?1")
    public int updateRole(Long id, Role role);

    public Optional<User> findByEmail(String email);
}
