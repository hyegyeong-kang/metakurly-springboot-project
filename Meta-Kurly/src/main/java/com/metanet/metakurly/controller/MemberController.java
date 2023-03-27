package com.metanet.metakurly.controller;

import com.metanet.metakurly.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.metanet.metakurly.dto.MemberDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/members")
@AllArgsConstructor
public class MemberController {

    private MemberService service;

    @GetMapping("")
    public List<MemberDTO> list() throws Exception {
        return service.getList();
    }

    @GetMapping("/signup")
    public void signUp() {}

    @PostMapping("/signup")
    public void signUp(@RequestBody MemberDTO member) throws Exception {
        service.signUp(member);
    }

    @GetMapping("/login")
    public void login() {}

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberDTO member, HttpSession session) throws Exception {
        MemberDTO dto = service.getMemberByUserId(member.getUserId());
        String status = member.getStatus();

        if (dto == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("해당하는 사용자가 없습니다.");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 암호화된 비밀번호 비교
        if (!encoder.matches(member.getPassword(), dto.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호가 일치하지 않습니다.");
        }

        session.setAttribute("member", dto);

        return ResponseEntity.ok("로그인 성공");

    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("로그아웃이 완료되었습니다.");
    }

    @GetMapping("/modify")
    public void modify() {}

    @PatchMapping("/modify/{m_id}")
    public void modify(@PathVariable("m_id") Long m_id, @RequestBody MemberDTO member) throws Exception {
        member.setM_id(m_id);
        service.modify(member);
    }

    @GetMapping("/delete")
    public void delete() {}

    @PatchMapping("/delete/{m_id}")
    public void delete(@PathVariable("m_id") Long m_id, @RequestBody MemberDTO member) throws Exception {
        member.setM_id(m_id);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String password = member.getPassword();
        String encodedPassword = encoder.encode(password);
        member.setPassword(encodedPassword);

        service.modify(member);
    }

}
