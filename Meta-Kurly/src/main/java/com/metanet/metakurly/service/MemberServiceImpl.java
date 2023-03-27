package com.metanet.metakurly.service;

import com.metanet.metakurly.dto.MemberDTO;
import com.metanet.metakurly.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private MemberMapper mapper;

    public List<MemberDTO> getList() throws Exception {
        return mapper.getList();
    }

    @Override
    public void signUp(MemberDTO member) throws Exception{
        String password = member.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        member.setPassword(encodedPassword);
        System.out.println(member);
        mapper.signUp(member);
    }

    @Override
    public MemberDTO getMemberByUserId(String userId) throws Exception {
        return mapper.getMemberByUserId(userId);
    }

    @Override
    public MemberDTO login(MemberDTO member) throws Exception {
        return mapper.login(member);
    }

    @Override
    public void logout(HttpSession session) throws Exception{
        session.invalidate();
    }

    @Override
    public int modify(MemberDTO member) throws Exception {
        String password = member.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        member.setPassword(encodedPassword);
        return mapper.modify(member);
    }

    //회원 탈퇴
    public int delete(MemberDTO member) throws Exception {
        return mapper.delete(member);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
