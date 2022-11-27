package com.env_protection.posting.controller;

import com.env_protection.posting.dtos.request.CreatePostingDto;
import com.env_protection.posting.dtos.response.PostingResponse;
import com.env_protection.posting.service.PostingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * com.env_protection.posting.controller
 * Created by NhatLinh - 19127652
 * Date 11/27/2022 - 12:14 AM
 * Description: ...
 */
@RestController
@RequestMapping("api/v1/posting")
@CrossOrigin
@AllArgsConstructor
public class PostingController {
    private final PostingService postingService;

    @PostMapping
    public ResponseEntity<?> addPosting(@RequestHeader("username") String username,
                                        @RequestBody CreatePostingDto request)
    {
        PostingResponse res = postingService.addNewPost(username, request);
        if (res == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }


    @GetMapping
    public ResponseEntity<?> getPostingByEventId(@RequestParam("eventId") String eventId)
    {
        List<PostingResponse> res = postingService.getPostsByEventId(eventId);
        if (res == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
