package com.demo.subscriptionservice.converter;

import com.demo.subscriptionservice.model.entity.SubscribedUserEntity;
import com.demo.subscriptionservice.model.request.CreateSubscriptionRequest;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;


@Mapper(componentModel = "spring")
public abstract class CreateSubscriptionRequestToSubscribedUserEntityConverter implements Converter<CreateSubscriptionRequest, SubscribedUserEntity> {

    public abstract SubscribedUserEntity convert(CreateSubscriptionRequest source);

}
