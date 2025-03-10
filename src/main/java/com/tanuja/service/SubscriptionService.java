package com.tanuja.service;

import com.tanuja.model.PlanType;
import com.tanuja.model.Subscription;
import com.tanuja.model.User;

public interface SubscriptionService {

	Subscription createSubscription(User user);

	Subscription getUserSubscription(Long userId)throws Exception;
	Subscription updateSubscription(Long userId, PlanType planType);
	boolean isValid(Subscription subscription);
}
