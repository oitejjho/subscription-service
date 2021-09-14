package com.demo.subscriptionservice.component;

import com.demo.subscriptionservice.model.entity.SubscribedUserEntity;
import com.demo.subscriptionservice.model.request.CreateSubscriptionRequest;
import com.demo.subscriptionservice.model.response.SubscriptionResponse;
import com.demo.subscriptionservice.service.persistence.SubscriptionPersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class SubscriptionComponent {

    private final SubscriptionPersistenceService subscriptionPersistenceService;
    private final ConversionService conversionService;

    public SubscriptionResponse createSubscription(CreateSubscriptionRequest request) {
        SubscribedUserEntity entity = this.conversionService.convert(request, SubscribedUserEntity.class);

        entity.setSubscriptionId(UUID.randomUUID().toString());
        entity.setCreated(LocalDateTime.now());
        SubscribedUserEntity persistedEntity = this.subscriptionPersistenceService.createSubscription(entity);

        SubscriptionResponse response = new SubscriptionResponse();
        response.setSubscriptionId(persistedEntity.getSubscriptionId());

        return response;
    }

    /*public Mono<UserResponse> createUser(CreateUserRequest request) {
        UserCommand userCommand = conversionService.convert(request, UserCommand.class);
        userCommand.setLevel(UserLevel.LEVEL_1);
        return userPersistenceService.save(userCommand)
                .map(item -> conversionService.convert(item, UserResponse.class));
    }

    public Mono<UserResponse> updateUser(long id, UpdateUserRequest request) {
        return userPersistenceService.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(
                        HttpConstants.USER_NOT_FOUND)))
                .map(userCommand -> {
                    if (StringUtils.isNotBlank(request.getCardId())) {
                        userCommand.setCardId(request.getCardId());
                    }
                    if (StringUtils.isNotBlank(request.getFirstName())) {
                        userCommand.setFirstName(request.getFirstName());
                    }
                    if (StringUtils.isNotBlank(request.getSecondName())) {
                        userCommand.setSecondName(request.getSecondName());
                    }
                    if (StringUtils.isNotBlank(request.getType())) {
                        userCommand.setType(UserType.find(request.getType())
                                .orElseThrow(() -> new ServiceException(HttpConstants.FAILED_TO_CONVERT_VALUE_TO_ENUM)));
                    }
                    if (request.getStatus() != null) {
                        userCommand.setStatus(UserStatus.find(request.getStatus())
                                .orElseThrow(() -> new ServiceException(HttpConstants.FAILED_TO_CONVERT_VALUE_TO_ENUM)));
                    }
                    if (StringUtils.isNotBlank(request.getDateOfBirth())) {
                        userCommand.setDateOfBirth(request.getDateOfBirth());
                    }
                    if (request.getAge() != null) {
                        userCommand.setAge(request.getAge());
                    }
                    return userCommand;
                })
                .flatMap(userPersistenceService::save)
                .map(item -> conversionService.convert(item, UserResponse.class));
    }

    public Mono<UserResponse> getUser(long id) {
        return userPersistenceService.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(HttpConstants.USER_NOT_FOUND)))
                .map(item -> conversionService.convert(item, UserResponse.class))
                .flatMap(userResponse -> boredapiClientService.getActivity()
                        .map(activityCommand -> conversionService.convert(activityCommand, ActivityResponse.class))
                        .map(userResponse::setActivity));
    }

    public Mono<CustomPage<UserShortResponse>> getUserList(Pageable pageable) {
        return userPersistenceService.findAll(pageable)
                .map(pageResponse -> pageResponse.map(item -> conversionService.convert(item, UserShortResponse.class)));
    }

    public void deleteUser(long id) {
        userPersistenceService.delete(id);
    }

    public Mono<String> generateRSA(Long id) {
        return userPersistenceService.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(HttpConstants.USER_NOT_FOUND)))
                .publishOn(Schedulers.boundedElastic())
                .flatMap(userCommand -> Mono.fromCallable(CryptoUtils::generateKeyPair))
                .map(keyPair -> Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
    }*/
}
