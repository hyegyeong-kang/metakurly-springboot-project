package com.metanet.metakurly.service;

import com.metanet.metakurly.dto.MemberDTO;
import com.metanet.metakurly.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberMapper mapper;

    @Override
    public List<MemberDTO> getList() {
        return mapper.getList();
    }

    @Override
    public int signUp(MemberDTO member) {
        return mapper.signUp(member);
    }

    @Override
    public MemberDTO login(MemberDTO member) throws Exception {
        return mapper.login(member);
    }

//    @Override
//    public void logout(HttpSession session) {
//        session.invalidate();
//    }

    @Override
    public int modify(MemberDTO member) throws Exception {
        return mapper.modify(member);
    }

    //회원 탈퇴
    public int delete(MemberDTO member) throws Exception {
        return mapper.delete(member);
    }
}
