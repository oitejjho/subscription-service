package com.demo.subscriptionservice.component;

import com.demo.subscriptionservice.model.entity.SubscribedUserEntity;
import com.demo.subscriptionservice.model.event.SubscriptionCompleteEvent;
import com.demo.subscriptionservice.model.request.CreateSubscriptionRequest;
import com.demo.subscriptionservice.model.response.SubscriptionCreateResponse;
import com.demo.subscriptionservice.model.response.SubscriptionListResponse;
import com.demo.subscriptionservice.model.response.SubscriptionResponse;
import com.demo.subscriptionservice.service.eventbus.RabbitEventSender;
import com.demo.subscriptionservice.service.persistence.SubscriptionPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class SubscriptionComponent {

    private final SubscriptionPersistenceService subscriptionPersistenceService;
    private final ConversionService conversionService;
    private final RabbitEventSender rabbitEventSender;

    public SubscriptionCreateResponse createSubscription(CreateSubscriptionRequest request) {
        SubscribedUserEntity entity = this.conversionService.convert(request, SubscribedUserEntity.class);

        entity.setSubscriptionId(UUID.randomUUID().toString());
        entity.setCreated(LocalDateTime.now());
        entity.setActiveFlag(true);
        SubscribedUserEntity persistedEntity = this.subscriptionPersistenceService.createSubscription(entity);

        SubscriptionCreateResponse response = new SubscriptionCreateResponse();
        response.setSubscriptionId(persistedEntity.getSubscriptionId());

        SubscriptionCompleteEvent event = new SubscriptionCompleteEvent();
        event.setEmail(entity.getEmail());
        event.setBody("Successfully subscribed");
        rabbitEventSender.send(event);

        return response;
    }

    public SubscriptionListResponse getSubscriptions(Pageable pageable) {
        Page<SubscribedUserEntity> subscribedUserEntityPage = this.subscriptionPersistenceService.getSubscriptions(pageable);
        List<SubscriptionResponse> subscriptionCreateResponses = subscribedUserEntityPage.getContent().stream().map(entity -> this.conversionService.convert(entity, SubscriptionResponse.class)).collect(Collectors.toList());

        SubscriptionListResponse subscriptionListResponse = new SubscriptionListResponse();
        subscriptionListResponse.setSubscriptions(subscriptionCreateResponses);
        subscriptionListResponse.setCurrentPage(pageable.getPageNumber());
        subscriptionListResponse.setPageSize(subscriptionCreateResponses.size());
        subscriptionListResponse.setTotalPage(subscribedUserEntityPage.getTotalPages());
        return subscriptionListResponse;
    }

    public SubscriptionResponse getSubscription(String subscriptionId) {
        SubscribedUserEntity subscribedUserEntity = this.subscriptionPersistenceService.getSubscription(subscriptionId);
        SubscriptionResponse subscriptionResponse = this.conversionService.convert(subscribedUserEntity, SubscriptionResponse.class);
        return subscriptionResponse;
    }

    public void cancelSubscription(String subscriptionId) {
        SubscribedUserEntity subscribedUserEntity = this.subscriptionPersistenceService.getSubscription(subscriptionId);
        subscribedUserEntity.setActiveFlag(false);
        this.subscriptionPersistenceService.updateSubscription(subscribedUserEntity);
    }
}
