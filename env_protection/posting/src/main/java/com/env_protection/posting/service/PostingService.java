package com.env_protection.posting.service;

import com.env_protection.posting.dtos.request.CreatePostingDto;
import com.env_protection.posting.dtos.response.PostingResponse;
import com.env_protection.posting.model.Posting;

import java.util.List;

/**
 * com.env_protection.posting.service
 * Created by NhatLinh - 19127652
 * Date 11/27/2022 - 12:15 AM
 * Description: ...
 */
public interface PostingService {
    List<PostingResponse> getPostsByEventId(String eventId);
    PostingResponse addNewPost(String username, CreatePostingDto posting);
}
