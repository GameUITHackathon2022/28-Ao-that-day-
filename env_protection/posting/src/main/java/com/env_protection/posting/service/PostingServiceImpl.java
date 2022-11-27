package com.env_protection.posting.service;

import com.env_protection.posting.dtos.request.CreatePostingDto;
import com.env_protection.posting.dtos.response.PostingResponse;
import com.env_protection.posting.model.Posting;
import com.env_protection.posting.model.PostingImage;
import com.env_protection.posting.repository.PostingImageRepository;
import com.env_protection.posting.repository.PostingRepository;
import com.linkho.clients.event.AttendEventRequestDto;
import com.linkho.clients.event.EventClient;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * com.env_protection.posting.service
 * Created by NhatLinh - 19127652
 * Date 11/27/2022 - 12:16 AM
 * Description: ...
 */
@Service
@AllArgsConstructor
@Transactional
public class PostingServiceImpl implements PostingService {
    private final PostingRepository postingRepository;
    private final PostingImageRepository postingImageRepository;
    private final EventClient eventClient;

    @Override
    public List<PostingResponse> getPostsByEventId(String eventId) {
        List<Posting> postings = postingRepository.getPostingByEventId(eventId);

        return postings.stream().map(e -> {
            List<PostingImage> images = postingImageRepository
                    .getPostingImageByPostingId(e.getId());
            return PostingResponse.builder()
                    .id(e.getId())
                    .description(e.getDescription())
                    .eventId(e.getEventId())
                    .imgPaths(images.stream().map(PostingImage::getImgPath)
                            .collect(Collectors.toList()))
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public PostingResponse addNewPost(String username, CreatePostingDto posting) {
        ResponseEntity<?> attendRes = eventClient.attendEvent(AttendEventRequestDto.builder()
                .eventId(posting.getEventId())
                .username(username)
                .build());
        if (attendRes.getStatusCode() != HttpStatus.OK)
            return null;
        LocalDateTime now =LocalDateTime.now();
        String id = UUID.randomUUID().toString();
        Posting created = postingRepository.saveAndFlush(Posting.builder()
                        .id(id)
                        .eventId(posting.getEventId())
                        .username(username)
                        .description(posting.getDescription())
                        .createdAt(now)
                        .modifiedAt(now).build());

        List<PostingImage> imgs = new ArrayList<>();
        for (var img : posting.getImgPaths())
        {
            PostingImage newImg = PostingImage.builder()
                    .posting(created)
                    .imgPath(img)
                    .build();
            postingImageRepository.save(newImg);
            imgs.add(newImg);
        }
        postingImageRepository.flush();

        return PostingResponse.builder()
                .id(created.getId())
                .description(created.getDescription())
                .eventId(created.getEventId())
                .imgPaths(imgs.stream().map(PostingImage::getImgPath).collect(Collectors.toList()))
                .build();
    }
}
