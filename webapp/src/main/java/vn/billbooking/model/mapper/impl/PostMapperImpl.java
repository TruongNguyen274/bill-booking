package vn.billbooking.model.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.billbooking.model.dto.KaraokeDTO;
import vn.billbooking.model.dto.PostDTO;
import vn.billbooking.model.entity.Karaoke;
import vn.billbooking.model.entity.Post;
import vn.billbooking.model.mapper.PostMapper;
import vn.billbooking.service.KaraokeService;
import vn.billbooking.service.PostService;

import java.util.ArrayList;
import java.util.List;


@Component
public class PostMapperImpl implements PostMapper {

    @Autowired
    PostService postService;

    @Autowired
    KaraokeService karaokeService;

    @Override
    public PostDTO toDTO(Post post) {
        PostDTO postDTO = new PostDTO();

        postDTO.setId(post.getId());
        postDTO.setName(post.getName());
        postDTO.setAvatar(post.getAvatar());
        postDTO.setDescription(post.getDescription());
        postDTO.setDetail(post.getDetail());
        postDTO.setStatus(post.isStatus());
        postDTO.setProgress(post.getProgress());

        KaraokeDTO karaokeDTO = new KaraokeDTO();
        Karaoke karaoke = post.getOwner();
        if (karaoke != null){
            karaokeDTO.setId(karaoke.getId());
            karaokeDTO.setName(karaoke.getName());
        }
        postDTO.setOwner(karaokeDTO);
        postDTO.setOwnerId(karaoke.getId());

        return postDTO;
    }

    @Override
    public List<PostDTO> toListDTO(List<Post> posts) {
        if (posts == null){
            return null;
        }
        List<PostDTO> list = new ArrayList<>(posts.size());
        for (Post post : posts) {
            list.add(toDTO(post));
        }
        return list;
    }

    @Override
    public Post toEntity(PostDTO postDTO) {
        Post post = postService.findById(postDTO.getId());

        if (post == null){
            post = new Post();
        }

        post.setName(postDTO.getName().trim());
        post.setDescription(postDTO.getDescription());
        post.setDetail(postDTO.getDetail());
        post.setStatus(postDTO.isStatus());
        post.setProgress(postDTO.getProgress());
        Karaoke karaoke = karaokeService.findById(postDTO.getOwnerId());
        post.setOwner(karaoke);
        return post;
    }
}
