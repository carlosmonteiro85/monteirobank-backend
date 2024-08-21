package com.monteirosltda.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.monteirosltda.domain.model.Token;

public interface TokenRepository extends JpaRepository<Token, Integer> {

  @Query(value = """
      SELECT t FROM Token t INNER JOIN User u
      ON t.user.id = u.id
      WHERE u.id = :id and (t.expired = false or t.revoked = false)
      """)
  List<Token> findAllValidTokenByUser(Integer id);

  Optional<Token> findByToken(String token);
}
