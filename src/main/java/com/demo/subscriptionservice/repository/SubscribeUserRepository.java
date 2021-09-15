package com.demo.subscriptionservice.repository;

import com.demo.subscriptionservice.model.entity.SubscribedUserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface SubscribeUserRepository extends PagingAndSortingRepository<SubscribedUserEntity, Long> {

    Page<SubscribedUserEntity> findAllByOrderByCreatedDesc(Pageable pageable);

    Optional<SubscribedUserEntity> findBySubscriptionId(String subscriptionId);

    Optional<SubscribedUserEntity> findByEmail(String email);

}
