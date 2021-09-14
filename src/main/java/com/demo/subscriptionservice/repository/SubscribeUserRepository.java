package com.demo.subscriptionservice.repository;

import com.demo.subscriptionservice.model.entity.SubscribedUserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeUserRepository extends JpaRepository<SubscribedUserEntity, Long> {

    Page<SubscribedUserEntity> findAllByOrderByCreatedDesc(Pageable pageable);

}
