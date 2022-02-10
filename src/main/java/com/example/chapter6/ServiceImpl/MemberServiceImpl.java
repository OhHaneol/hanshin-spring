package com.example.chapter6.ServiceImpl;

import com.example.chapter6.mapper.MemberMapper;
import com.example.chapter6.model.MemberVO;
import com.example.chapter6.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    /**
     * 아이디 중복 체크
     * @param id
     * @return
     */
    @Override
    public Boolean duplicateId(String id) {
        Boolean res = memberMapper.duplicateId(id);
        return res? true:false;
    }

    /**
     * 이메일 중복 체크
     * @param email
     * @return
     */
    @Override
    public Boolean duplicateEmail(String email) {
        Boolean res = memberMapper.duplicateEmail(email);
        return res? true:false;
    }

    /**
     * 회원 가입 처리
     * @param memberVO
     */
    @Override
    public void insertMember(MemberVO memberVO) throws Exception {
        memberMapper.insertMember(memberVO);
    }

    /**
     * 로그인 처리
     * @param memberVO
     * @return
     */
    @Override
    public Boolean loginProcess(MemberVO memberVO, HttpServletRequest request) {
        MemberVO result = memberMapper.loginProcess(memberVO);

        if(result != null) {
            // 세션 정보 생성
            HttpSession session = request.getSession();
            session.setAttribute("memberVO", result);
            // 1시간 동안(60s * 60) 동작 없으면 세션 삭제
            session.setMaxInactiveInterval(60 * 60);
            return true;
        }

        return false;
    }

    /**
     * 아이디 찾기
     * @param memberVO
     * @return
     */
    @Override
    public String findUserId(MemberVO memberVO) {
        return memberMapper.findUserId(memberVO);
    }

    /**
     * 비밀번호 찾기
     * @param memberVO
     * @return
     */
    @Override
    public String findPassword(MemberVO memberVO) {
        return memberMapper.findPassword(memberVO);
    }

    /**
     * 비밀번호 업데이트
     * @param memberVO
     */
    @Override
    public void updatePassword(MemberVO memberVO) {
        memberMapper.updatePassword(memberVO);
    }

    /**
     * 회원가입 폼 검증
     * @param errors
     * @return
     */
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
