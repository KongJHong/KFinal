package com.hong.services.services.impl;

import com.hong.repository.dto.TagDto;
import com.hong.repository.entity.Tag;
import com.hong.repository.mapper.TagMapper;
import com.hong.services.services.ITagService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : KongJHong
 * @Date : 2020-09-26 17:32
 * @Version : 1.0
 * Description     :
 */
@Service
public class TagServiceImpl implements ITagService {

    private final TagMapper tagMapper;

    public TagServiceImpl(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    @Override
    public List<TagDto> findAll() {
        List<Tag> tags = tagMapper.findAll();
        List<TagDto> dtos = new ArrayList<>();

        if (tags != null && tags.size() > 0){
            for (Tag tag: tags) {
                dtos.add(new TagDto(tag));
            }
        }
        return dtos;
    }
}
