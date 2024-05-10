package vn.billbooking.model.mapper;

import vn.billbooking.model.dto.PostDTO;
import vn.billbooking.model.entity.Post;
import vn.billbooking.model.entity.Room;

import java.util.List;

public interface PostMapper {

    //Map Entity to DTO
    PostDTO toDTO(Post post);

    List<PostDTO> toListDTO(List<Post> posts);

    //Map DTO to Entity
    Post toEntity(PostDTO postDTO);


}
