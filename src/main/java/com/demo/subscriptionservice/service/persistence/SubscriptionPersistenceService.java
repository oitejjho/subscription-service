package com.demo.subscriptionservice.service.persistence;

import com.demo.subscriptionservice.model.entity.SubscribedUserEntity;
import com.demo.subscriptionservice.repository.SubscribeUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionPersistenceService {

    private final SubscribeUserRepository subscribeUserRepository;

    public SubscribedUserEntity createSubscription(SubscribedUserEntity entity) {
        SubscribedUserEntity persistedEntity = this.subscribeUserRepository.save(entity);
        return persistedEntity;
    }

    /*public Mono<UserCommand> save(UserCommand userCommand) {
        UserEntity userEntity = conversionService.convert(userCommand, UserEntity.class);
        return userRepository.save(userEntity)
                .map(item -> {
                    userCommand.setId(item.getId());
                    return userCommand;
                });
    }

    public Mono<UserCommand> findById(long id) {
        return userRepository.findById(id)
                .map(item -> conversionService.convert(item, UserCommand.class));
    }

    public Mono<CustomPage<UserCommand>> findAll(Pageable pageable) {
        Mono<Long> countMono = userRepository.count();
        int skip = pageable.getPageNumber() == 1 ? 0 : pageable.getPageNumber() * pageable.getPageSize();
        Mono<List<UserCommand>> userCommandFlux = userRepository.findAll(skip, pageable.getPageSize())
                .map(item -> conversionService.convert(item, UserCommand.class))
                .collectList();

        return Mono.zip(countMono, userCommandFlux, (aLong, list) -> new CustomPage<UserCommand>()
                .setContent(list)
                .setNumber(pageable.getPageNumber())
                .setSize(list.size())
                .setTotalElements(aLong));
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }*/

}
