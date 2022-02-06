package com.example.chapter6.ServiceImpl;

import com.example.chapter6.mapper.MemberMapper;
import com.example.chapter6.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

//  여기는 Service 레이어고 비즈니스 로직을 작성할 것이라고 선언한 것
@Service
public class MemberServiceImpl implements MemberService {

//    @Autowired  //  MemverService 파일을 호출하기 위함. 그러나 밑에 Constructor Inject 를 사용했으므로 지워줌.
    private MemberMapper memberMapper;

    public MemberServiceImpl(MemberMapper memberMapper) {   // Constructor Inject
        this.memberMapper = memberMapper;
    }

    /*
    *  아이디 중복 체크
    * */
    @Override
    public Boolean duplicateId(String id) {
        Boolean res = memberMapper.duplicateId(id);
        return res? true:false;
    }

    /*
    * 회원가입 폼 검증
    * */
    @Override
    public Map<String, String> formValidation(Errors errors) {
        Map<String, String> result = new HashMap<>();

        for(FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            result.put(validKeyName, error.getDefaultMessage());
        }

        return result;
    }
}
