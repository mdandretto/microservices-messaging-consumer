package com.github.mdandretto.microservices.messagingconsumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.mdandretto.microservices.messagingconsumer.domain.Transaction;

@Repository
public interface UserRepository extends JpaRepository<Transaction, Long> {
}
