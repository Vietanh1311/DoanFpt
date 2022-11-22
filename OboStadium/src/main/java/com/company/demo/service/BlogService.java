package com.company.demo.service;

import com.company.demo.entity.Post;
import com.company.demo.entity.User;
import com.company.demo.model.dto.PageableDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface BlogService {


    public Page<Post> getListPost(int page);

    public Post getPostById(long id);

    public List<Post> getLatestPostsNotId(long id);

}
