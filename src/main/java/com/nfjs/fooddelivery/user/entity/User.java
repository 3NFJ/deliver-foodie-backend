package com.nfjs.fooddelivery.user.entity;

import com.nfjs.fooddelivery.common.entity.BaseEntity;
import com.nfjs.fooddelivery.deliveryaddress.entity.DeliveryAddress;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

  @Column(name = "token_created_at")
  private LocalDateTime tokenCreatedAt;

  @OneToMany(mappedBy = "user")
  private List<DeliveryAddress> deliveryAddressList = new ArrayList<>();

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
