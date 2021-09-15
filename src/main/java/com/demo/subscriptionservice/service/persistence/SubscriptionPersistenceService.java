package com.demo.subscriptionservice.service.persistence;

import com.demo.subscriptionservice.constants.StatusConstants;
import com.demo.subscriptionservice.exceptions.InvalidRequestException;
import com.demo.subscriptionservice.model.entity.SubscribedUserEntity;
import com.demo.subscriptionservice.model.response.SubscriptionResponse;
import com.demo.subscriptionservice.repository.SubscribeUserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.demo.subscriptionservice.constants.StatusConstants.HttpConstants.DUPLICATE_EMAIL_ERROR;

@Service
@RequiredArgsConstructor
public class SubscriptionPersistenceService {

    private static final Logger LOG = LogManager.getLogger(SubscriptionPersistenceService.class);

    private final SubscribeUserRepository subscribeUserRepository;

    public SubscribedUserEntity createSubscription(SubscribedUserEntity entity) {
        Optional<SubscribedUserEntity> existingEntity = this.subscribeUserRepository.findByEmail(entity.getEmail());
        if(existingEntity.isPresent())
            throw new InvalidRequestException(DUPLICATE_EMAIL_ERROR);
        SubscribedUserEntity persistedEntity = this.subscribeUserRepository.save(entity);
        return persistedEntity;
    }

    public Page<SubscribedUserEntity> getSubscriptions(Pageable pageable) {
       return this.subscribeUserRepository.findAllByOrderByCreatedDesc(pageable);
    }

    public SubscribedUserEntity getSubscription(String subscriptionId) {
        Optional<SubscribedUserEntity> subscribedUserEntityOptional = this.subscribeUserRepository.findBySubscriptionId(subscriptionId);
        if(subscribedUserEntityOptional.isEmpty())
            throw new NoSuchElementException();
        return subscribedUserEntityOptional.get();
    }

    public void updateSubscription(SubscribedUserEntity entity) {
        this.subscribeUserRepository.save(entity);
    }

}
