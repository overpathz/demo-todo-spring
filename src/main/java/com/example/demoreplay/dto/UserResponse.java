package com.example.demoreplay.dto;

import com.example.demoreplay.entity.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
@Builder
public class UserResponse {
    private List<User> userList;
    private int contentSize;
}
