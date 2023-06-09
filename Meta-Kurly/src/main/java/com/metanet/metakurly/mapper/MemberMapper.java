package com.metanet.metakurly.mapper;

import org.apache.ibatis.annotations.Mapper;

import javax.servlet.http.HttpSession;

import com.metanet.metakurly.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {


    //테스트를 위해 회원 정보 읽어오기
    public List<MemberDTO> getList() throws Exception;

    //회원가입
    public void signUp(MemberDTO member) throws Exception;

    public MemberDTO getMemberByUserId(String userId) throws Exception;

    //로그인
    public MemberDTO login(MemberDTO member) throws Exception;

    //로그아웃
    public void logout(HttpSession session) throws Exception;

    //회원정보 수정
    public int modify(MemberDTO member) throws Exception;

    //회원 탈퇴
    public int delete(MemberDTO member) throws Exception;
}
