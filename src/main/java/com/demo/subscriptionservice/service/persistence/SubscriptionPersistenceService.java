package com.demo.subscriptionservice.service.persistence;

import com.demo.subscriptionservice.constants.StatusConstants;
import com.demo.subscriptionservice.exceptions.InvalidRequestException;
import com.demo.subscriptionservice.model.entity.SubscribedUserEntity;
import com.demo.subscriptionservice.repository.SubscribeUserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import static com.demo.subscriptionservice.constants.StatusConstants.HttpConstants.DUPLICATE_EMAIL_ERROR;

@Service
@RequiredArgsConstructor
public class SubscriptionPersistenceService {

    private static final Logger LOG = LogManager.getLogger(SubscriptionPersistenceService.class);

    private final SubscribeUserRepository subscribeUserRepository;

    public SubscribedUserEntity createSubscription(SubscribedUserEntity entity) {
        try {
            SubscribedUserEntity persistedEntity = this.subscribeUserRepository.save(entity);
            return persistedEntity;
        } catch (DataIntegrityViolationException ex) {
            LOG.error(ex.getMessage());
            throw new InvalidRequestException(DUPLICATE_EMAIL_ERROR);
        }
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
