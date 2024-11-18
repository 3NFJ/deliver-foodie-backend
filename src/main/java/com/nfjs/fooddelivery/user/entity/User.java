package com.nfjs.fooddelivery.user.entity;

import com.nfjs.fooddelivery.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "p_users")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", nullable = false, updatable = false)
  private Long userId;

  @Column(name = "user_number", columnDefinition = "uuid")
  private UUID userNumber;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String username;

  @Column(nullable = false, unique = true)
  private String nickname;

  @Column(name = "phone_number", nullable = false, unique = true)
  private String phoneNumber;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private UserRoleEnum role;

  @Column(name = "token_created_at", nullable = true) // 최초 회원가입시 null
  private LocalDateTime tokenCreatedAt;

  public void updateTokenCreatedAt(LocalDateTime time) {  // 토큰 생성 시간 업데이트
    this.tokenCreatedAt = time;
  }

  public boolean isValidTokenCreatedAt() { // 토큰 생성 시간 유효성 검증
    if (this.tokenCreatedAt == null) {
      return false;
    }
    return this.tokenCreatedAt.plusDays(7).isAfter(LocalDateTime.now());
  }

  @PrePersist
  public void generateUserNumber() {
    if (this.userNumber == null) {
      this.userNumber = UUID.randomUUID();
    }
  }

  @Builder
  public User(
      UUID userNumber, String email, String password, String username, String nickname, String phoneNumber, UserRoleEnum role
  ) {
    this.userNumber = (userNumber != null) ? userNumber : UUID.randomUUID();
    this.email = email;
    this.password = password;
    this.username = username;
    this.nickname = nickname;
    this.phoneNumber = phoneNumber;
    this.role = role;
    this.tokenCreatedAt = getTokenCreatedAt();
  }
}
