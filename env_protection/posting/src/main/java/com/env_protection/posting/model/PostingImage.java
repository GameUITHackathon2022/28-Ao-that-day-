package com.env_protection.posting.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * com.env_protection.posting.model
 * Created by NhatLinh - 19127652
 * Date 11/27/2022 - 12:22 AM
 * Description: ...
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostingImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    private Posting posting;
    private String imgPath;
}
