package com.batrawy.LibraryManagementSystem.service;

import com.batrawy.LibraryManagementSystem.model.dto.MemberRequest;
import com.batrawy.LibraryManagementSystem.model.dto.MemberResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MemberService {
    public ResponseEntity<String> addMember(MemberRequest memberRequest);
    public ResponseEntity<String> deleteMember(Long id);
    public ResponseEntity<String> updateMember(Long id, MemberRequest memberRequest);
    public ResponseEntity<List<MemberResponse>> getAllMembers();
    public ResponseEntity<?> getMemberById(Long id);
}
