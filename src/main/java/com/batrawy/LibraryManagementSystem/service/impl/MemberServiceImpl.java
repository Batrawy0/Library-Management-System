package com.batrawy.LibraryManagementSystem.service.impl;

import com.batrawy.LibraryManagementSystem.model.dto.MemberRequest;
import com.batrawy.LibraryManagementSystem.model.dto.MemberResponse;
import com.batrawy.LibraryManagementSystem.model.entity.Member;
import com.batrawy.LibraryManagementSystem.repository.MemberRepository;
import com.batrawy.LibraryManagementSystem.service.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class MemberServiceImpl implements MemberService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@(.+)$"
    );

    private final ModelMapper modelMapper;
    MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<String> addMember(MemberRequest memberRequest) {
        try {
            if (isValidMemberInfo(memberRequest)) {
                if(isEmailValid(memberRequest.getEmail())){
                Member member = createNewMember(memberRequest);
                memberRepository.save(member);
                return ResponseEntity.status(HttpStatus.OK).body("Member Added Successfully");
                }else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email not valid");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Member createNewMember(MemberRequest memberRequest) {
        return modelMapper.map(memberRequest, Member.class);
    }

    private boolean isValidMemberInfo(MemberRequest memberRequest) {
        return memberRequest.getName() != null && memberRequest.getEmail() != null;
    }

    private boolean isEmailValid(String email) {
        return EMAIL_PATTERN.matcher(email).matches();

    }

    @Override
    public ResponseEntity<String> deleteMember(Long id) {
        try {
            if (idExist(id)) {
                memberRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body("Member deleted Successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Member doesn't exist");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean idExist(Long id) {
        return memberRepository.existsById(id);
    }

    @Override
    public ResponseEntity<String> updateMember(Long id, MemberRequest memberRequest) {
        try {
            if (idExist(id)) {
                if (isValidMemberInfo(memberRequest)) {

                    Member member = getMember(id);
                    memberRepository.save(updateMemberDetails(member, memberRequest));
                    return ResponseEntity.status(HttpStatus.OK).body("Member Updated Successfully");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid info");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Member doesn't exist");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Member getMember(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Member not found"));
    }

    private Member updateMemberDetails(Member member, MemberRequest memberRequest) {
        member.setName(memberRequest.getName());
        member.setEmail(memberRequest.getEmail());
        return member;
    }

    @Override
    public ResponseEntity<List<MemberResponse>> getAllMembers() {
        try {
            List<MemberResponse> memberResponses = new ArrayList<>();
            memberRepository.findAll().forEach(member -> {
                memberResponses.add(modelMapper.map(member, MemberResponse.class));
            });
            return ResponseEntity.status(HttpStatus.OK).body(memberResponses);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<?> getMemberById(Long id) {
        try {
            if (idExist(id)) {
                Member member = getMember(id);
                return ResponseEntity.status(HttpStatus.OK).body(getMemberResponse(member));
            } else {
                return ResponseEntity.status(HttpStatus.OK).body("Member doesn't exist");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private MemberRequest getMemberRequest(Member member) {
        return modelMapper.map(member, MemberRequest.class);
    }

    private MemberResponse getMemberResponse(Member member) {
        return modelMapper.map(member, MemberResponse.class);
    }
}
