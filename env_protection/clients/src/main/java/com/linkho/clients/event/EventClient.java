package com.linkho.clients.event;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * com.linkho.clients.event
 * Created by NhatLinh - 19127652
 * Date 11/27/2022 - 12:43 AM
 * Description: ...
 */
@FeignClient("event")
public interface EventClient {
    @PostMapping("api/v1/event/attend")
    ResponseEntity<?> attendEvent(@RequestBody AttendEventRequestDto request);
}
