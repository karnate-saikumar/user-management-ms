package com.kuber.user.main.jpa;

import com.kuber.user.main.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    <S extends User> List<S> findAll(Example<S> example);

    @Override
    Optional<User> findById(Long aLong);

    @Override
    void delete(User entity);

    @Override
    void deleteById(Long aLong);

    @Override
    <S extends User> S save(S entity);

}
