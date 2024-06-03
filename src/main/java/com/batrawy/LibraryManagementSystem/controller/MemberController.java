package com.batrawy.LibraryManagementSystem.controller;

import com.batrawy.LibraryManagementSystem.model.dto.MemberRequest;
import com.batrawy.LibraryManagementSystem.model.dto.MemberResponse;
import com.batrawy.LibraryManagementSystem.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/library/members/")
public class MemberController {

    MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addMember(@RequestBody MemberRequest memberRequest){
        return memberService.addMember(memberRequest);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteMember(@PathVariable Long id){
        return memberService.deleteMember(id);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<String> updateMember(@PathVariable Long id, @RequestBody MemberRequest memberRequest){
        return memberService.updateMember(id , memberRequest);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MemberResponse>> getAllBooks(){
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getBookById(@PathVariable Long id){
        return memberService.getMemberById(id);
    }
}
