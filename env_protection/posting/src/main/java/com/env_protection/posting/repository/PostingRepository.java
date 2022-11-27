package com.env_protection.posting.repository;

import com.env_protection.posting.model.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * com.env_protection.posting.repository
 * Created by NhatLinh - 19127652
 * Date 11/27/2022 - 12:16 AM
 * Description: ...
 */
@Repository
public interface PostingRepository extends JpaRepository<Posting, String> {

    @Query(value = "SELECT p.* FROM posting p WHERE p.event_id = ?1", nativeQuery = true)
    List<Posting> getPostingByEventId(String eventId);
}