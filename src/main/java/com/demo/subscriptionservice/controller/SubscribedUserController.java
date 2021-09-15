package com.demo.subscriptionservice.controller;


import com.demo.subscriptionservice.annotations.IsEnum;
import com.demo.subscriptionservice.annotations.Required;
import com.demo.subscriptionservice.component.SubscriptionComponent;
import com.demo.subscriptionservice.constants.StatusConstants;
import com.demo.subscriptionservice.enums.SubscriptionAction;
import com.demo.subscriptionservice.model.Response;
import com.demo.subscriptionservice.model.Status;
import com.demo.subscriptionservice.model.request.CreateSubscriptionRequest;
import com.demo.subscriptionservice.model.response.SubscriptionCreateResponse;
import com.demo.subscriptionservice.model.response.SubscriptionListResponse;
import com.demo.subscriptionservice.model.response.SubscriptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@CrossOrigin
@RequestMapping("/v1")
@RequiredArgsConstructor
@ResponseBody
public class SubscribedUserController {

    private final SubscriptionComponent subscriptionComponent;


    @GetMapping(path = "/subscriptions")
    @ResponseStatus(HttpStatus.OK)
    public Response<SubscriptionListResponse> getSubscriptions(Pageable pageable) {
        SubscriptionListResponse response = subscriptionComponent.getSubscriptions(pageable);
        return new Response<>(new Status(StatusConstants.HttpConstants.SUCCESS), response);
    }

    @GetMapping(path = "/subscriptions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response<SubscriptionResponse> getSubscription(@PathVariable String id) {
        SubscriptionResponse response = subscriptionComponent.getSubscription(id);
        return new Response<>(new Status(StatusConstants.HttpConstants.SUCCESS), response);
    }

    @PostMapping(path = "/subscriptions")
    @ResponseStatus(HttpStatus.CREATED)
    public Response<SubscriptionCreateResponse> createSubscription(@Valid @RequestBody CreateSubscriptionRequest request) {
        SubscriptionCreateResponse response = subscriptionComponent.createSubscription(request);
        return new Response<>(new Status(StatusConstants.HttpConstants.SUCCESS), response);
    }

    @PostMapping(path = "/subscriptions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Response<SubscriptionCreateResponse> cancelSubscription(
            @PathVariable String id,
            @RequestParam(name = "action", required = false)
            @Required(exception = StatusConstants.HttpConstants.ACTION_IS_REQUIRED)
            @IsEnum(enumClass = SubscriptionAction.class, exception = StatusConstants.HttpConstants.ACTION_IS_INVALID)
                    String action) {
        subscriptionComponent.cancelSubscription(id);
        return new Response<>(new Status(StatusConstants.HttpConstants.SUCCESS), null);
    }

}
