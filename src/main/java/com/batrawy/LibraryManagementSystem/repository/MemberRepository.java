package com.batrawy.LibraryManagementSystem.repository;

import com.batrawy.LibraryManagementSystem.model.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
}
