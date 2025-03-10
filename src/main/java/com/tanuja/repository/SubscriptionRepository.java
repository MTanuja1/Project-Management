package com.tanuja.repository;

import com.tanuja.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {

	Subscription findByUserId(Long userId);
}
