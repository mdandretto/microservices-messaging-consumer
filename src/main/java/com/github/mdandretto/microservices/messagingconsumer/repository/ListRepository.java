package com.github.mdandretto.microservices.messagingconsumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.mdandretto.microservices.messagingconsumer.domain.ItemList;

@Repository
public interface ListRepository extends JpaRepository<ItemList, Long> {
}
