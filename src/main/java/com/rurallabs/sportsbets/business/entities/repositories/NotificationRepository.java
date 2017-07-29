package com.rurallabs.sportsbets.business.entities.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rurallabs.sportsbets.business.entities.Notification;

@Repository
public interface NotificationRepository extends PagingAndSortingRepository<Notification, Long> {

	// No methods to add
}
