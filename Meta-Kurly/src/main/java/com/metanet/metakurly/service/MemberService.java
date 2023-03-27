package com.metanet.metakurly.service;

import com.metanet.metakurly.dto.MemberDTO;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface MemberService {

    public List<MemberDTO> getList() throws Exception;

    //회원가입
    public void signUp(MemberDTO member) throws Exception;

    public MemberDTO getMemberById(MemberDTO member) throws Exception;

    //로그인
    public MemberDTO login(MemberDTO member) throws Exception;

    //로그아웃
    public void logout(HttpSession session) throws Exception;

    //회원정보 수정
    public int modify(MemberDTO member) throws Exception;

    //회원 탈퇴
    public int delete(MemberDTO member) throws Exception;
}
