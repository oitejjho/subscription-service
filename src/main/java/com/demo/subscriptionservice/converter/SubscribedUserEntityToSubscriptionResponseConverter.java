package com.demo.subscriptionservice.converter;

import com.demo.subscriptionservice.model.entity.SubscribedUserEntity;
import com.demo.subscriptionservice.model.request.CreateSubscriptionRequest;
import com.demo.subscriptionservice.model.response.SubscriptionResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;


@Mapper(componentModel = "spring")
public abstract class SubscribedUserEntityToSubscriptionResponseConverter implements Converter<SubscribedUserEntity, SubscriptionResponse> {

    public abstract SubscriptionResponse convert(SubscribedUserEntity source);

}
