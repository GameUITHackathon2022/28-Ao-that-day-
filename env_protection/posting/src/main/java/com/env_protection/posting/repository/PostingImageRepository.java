package com.env_protection.posting.repository;

import com.env_protection.posting.model.Posting;
import com.env_protection.posting.model.PostingImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * com.env_protection.posting.repository
 * Created by NhatLinh - 19127652
 * Date 11/27/2022 - 1:07 AM
 * Description: ...
 */
public interface PostingImageRepository extends JpaRepository<PostingImage, Long> {

    @Query(value = "SELECT pi.* FROM posting_image pi WHERE pi.posting_id = ?1", nativeQuery = true)
    List<PostingImage> getPostingImageByPostingId(String postingId);
}
