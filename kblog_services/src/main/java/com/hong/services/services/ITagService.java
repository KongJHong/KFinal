package com.hong.services.services;

import com.hong.repository.dto.TagDto;

import java.util.List;

/**
 * @Author : KongJHong
 * @Date : 2020-09-26 17:31
 * @Version : 1.0
 * Description     :
 */
public interface ITagService {

    List<TagDto> findAll();
}
