package com.example.pro.pracPackage.elasticSearch;

import java.util.List;
import java.util.stream.Collectors;

import com.example.pro.pracPackage.elasticSearch.domain.User;
import com.example.pro.pracPackage.elasticSearch.domain.UserRepository;
import com.example.pro.pracPackage.elasticSearch.dto.UserRequestDto;
import com.example.pro.pracPackage.elasticSearch.dto.UserResponseDto;
import com.example.pro.pracPackage.elasticSearch.search.UserSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserSearchRepository userSearchRepository;

    @Transactional
    public Long save(UserRequestDto userRequestDto) {
        User user = new User(userRequestDto.getName(), userRequestDto.getDescription());
        User savedUser = userRepository.save(user);
//        userSearchRepository.save(user);
        return savedUser.getId();
    }

    public List<UserResponseDto> searchByName(String name, Pageable pageable) {
        // userSearchRepository.findByBasicProfile_NameContains(name) 가능
        return userSearchRepository.searchByName(name, pageable)
                .stream()
                .map(UserResponseDto::from)
                .collect(Collectors.toList());
    }
}