package com.max.website.model

import org.hibernate.annotations.GenericGenerator
import jakarta.persistence.*
import java.util.*
import org.hibernate.Hibernate
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import jakarta.persistence.GeneratedValue

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
data class User (
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false, unique = true)
    var username: String? = null,

    @Column(nullable = false)
    var password: String? = null,

    @Column(nullable = false, unique = true)
    var email: String? = null,

    @ElementCollection(fetch = FetchType.EAGER)
    var roles: Set<String> = HashSet(),

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private var createdAt: Date? = null,

    @UpdateTimestamp
    @Column(name = "updated_at")
    private var updatedAt: Date? = null

) : UserDetails {

   /*   The method "getAuthorities()" returns the user's roles list; it is helpful to manage permissions.

        We return an empty list because we will not cover role-based access control.

        The method "getUsername()" returns the email address because it is unique information about the user.

      💡
        Make sure the method isAccountNonExpired(), isAccountNonLocked(), isCredentialsNonExpired(), and isEnabled() returns "true";

        otherwise, the authentication will fail. You can customize the logic of these methods to fit your needs.
        https://blog.tericcabrel.com/jwt-authentication-springboot-spring-security/
      */


    override fun getAuthorities(): Collection<GrantedAuthority> {
        return listOf()
    }

    override fun getPassword(): String {
        return password!!
    }

    override fun getUsername(): String {
        return email!!
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as User

        return id != UUID(0, 0) && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , username = $username , email = $email)"
    }
}