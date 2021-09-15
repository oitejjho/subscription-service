package com.demo.subscriptionservice.controller;


import com.demo.subscriptionservice.component.SubscriptionComponent;
import com.demo.subscriptionservice.constants.StatusConstants;
import com.demo.subscriptionservice.model.Response;
import com.demo.subscriptionservice.model.Status;
import com.demo.subscriptionservice.model.request.CreateSubscriptionRequest;
import com.demo.subscriptionservice.model.response.SubscriptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Validated
@RestController
@CrossOrigin
@RequestMapping("/v1")
@RequiredArgsConstructor
@ResponseBody
public class SubscribedUserController {

    private final SubscriptionComponent subscriptionComponent;

    @PostMapping(path = "/subscriptions")
    @ResponseStatus(HttpStatus.CREATED)
    public Response<SubscriptionResponse> createSubscription(@Valid @RequestBody CreateSubscriptionRequest request, HttpServletResponse httpServletResponse) {
        SubscriptionResponse response = subscriptionComponent.createSubscription(request);
        return new Response<>(new Status(StatusConstants.HttpConstants.SUCCESS), response);
    }

    /*@PatchMapping(path = "/v1/users/{id}")
    public Mono<Response<UserResponse>> updateUser(@PathVariable Long id,
                                                   @Valid @RequestBody UpdateUserRequest request) {
        Mono<UserResponse> userResponseMono = userComponent.updateUser(id, request);
        return success(userResponseMono);
    }

    @GetMapping(path = "/v1/users/{id}")
    public Mono<Response<UserResponse>> getUser(@PathVariable Long id) {
        Mono<UserResponse> userResponseMono = userComponent.getUser(id);
        return success(userResponseMono);
    }

    @GetMapping(path = "/v1/users")
    public Mono<Response<CustomPage<UserShortResponse>>> getUserList(@RequestParam(required = false, defaultValue = "1") Integer page,
                                                                     @RequestParam(required = false, defaultValue = "10") Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Mono<CustomPage<UserShortResponse>> pageMono = userComponent.getUserList(pageRequest);
        return success(pageMono);
    }

    @DeleteMapping(path = "/v1/users/{id}")
    public Mono<Response<Void>> deleteUser(@PathVariable Long id) {
        userComponent.deleteUser(id);
        return success();
    }

    @PostMapping(path = "/v1/users/{id}/generate-rsa")
    public Mono<Response<String>> generateRSA(@PathVariable Long id) {
        Mono<String> rsa = userComponent.generateRSA(id);
        return success(rsa);
    }*/

}
