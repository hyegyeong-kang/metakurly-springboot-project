package com.metanet.metakurly.controller;

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
    public List<MemberDTO> list() {
        return service.getList();
    }

    @GetMapping("/signup")
    public void signUp() {}

    @PostMapping("/signup")
    public void signUp(@RequestBody MemberDTO member) throws Exception {
        service.signUp(member);
    }

    @GetMapping("/modify")
    public void modify() {}

    @PutMapping("/modify")
    public void modify(@RequestBody MemberDTO member) throws Exception {
        service.modify(member);
    }

    @GetMapping("/delete")
    public void delete() {}

    @PutMapping("/delete")
    public void delete(@RequestBody MemberDTO member) throws Exception {
        service.delete(member);
    }

//    @PutMapping("/modify/{m_id}")
//    public void modify(@PathVariable("m_id") Long m_id, @RequestBody MemberDTO member) throws Exception {
//        service.modify(m_id, member);
//    }
}
