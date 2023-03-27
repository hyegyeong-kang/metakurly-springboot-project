package com.metanet.metakurly.controller;

import com.metanet.metakurly.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.metanet.metakurly.dto.MemberDTO;
import com.metanet.metakurly.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<MemberDTO> login(@RequestBody MemberDTO member) throws Exception {
        //System.out.println(member);
        MemberDTO memberDTO = service.getMemberById(member);
        String status = memberDTO.getStatus();
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        if (status.equals("active") && memberDTO != null) {
            System.out.println(memberDTO);
            memberDTO =  service.login(member);
            httpStatus = HttpStatus.OK;
        } else {
            memberDTO =  null;
        }

        return ResponseEntity.status(httpStatus).body(memberDTO);
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
        service.delete(member);
    }


//    @PutMapping("/modify/{m_id}")
//    public void modify(@PathVariable("m_id") Long m_id, @RequestBody MemberDTO member) throws Exception {
//        service.modify(m_id, member);
//    }
}
