package com.example.pro.domain.entity;

import com.example.pro.dto.MemberGenderRequestDto;
import com.example.pro.enumPackage.Gender;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GenderTest{

    @Test
    void genderTest() {
        String genderFromApi = "0";
//        String genderMaleFromApi = "2";
        boolean isMale = genderFromApi.equals("1");

        Gender genderMale = isMale ? Gender.MALE
                : Gender.FEMALE;

        Assertions.assertThat(genderMale.getCode(isMale)).isEqualTo("4");

    }

    @Test
    void GenderEntityTest() {
        MemberGenderRequestDto target = MemberGenderRequestDto.builder()
                .gender("1")
                .name("테스트유저")
                .build();

        boolean isMale = target.getGender().equals("1");
        Gender gender = isMale ? Gender.MALE : Gender.FEMALE;
        MemberGenderRequestDto.MemberGender member = MemberGenderRequestDto.MemberGender.builder()
                .gender(gender.getCode(isMale))
                .name(target.getName())
                .build();
        Assertions.assertThat(member.getGender()).isEqualTo("1");
    }

}
