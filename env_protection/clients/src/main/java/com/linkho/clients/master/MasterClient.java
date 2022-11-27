package com.linkho.clients.master;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * com.linkho.master
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 3:56 PM
 * Description: ...
 */
@FeignClient("master")
public interface MasterClient {
    @GetMapping(path = "api/v1/master/eventType/{eventTypeId}")
    ResponseEntity<EventTypeResponse> getEvent(@PathVariable("eventTypeId") String eventTypeId);
}

