package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain;

import java.time.Instant;
import java.util.List;

import org.apache.catalina.security.SecurityUtil;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Email(message = "Email not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @NotNull
    @Size(min = 3, message = "Password phải tối thiểu 3 kí tự")
    private String password;

    @NotNull()
    @Size(min = 3, message = "Full Name phải tối thiểu 3 kí tự")
    private String fullName;

    private String address;
    private String phone;
    private String avatar;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss a", timezone = "GMT+7")
    private Instant createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss a", timezone = "GMT+7")
    private Instant updatedAt;

    private String createdBy;
    private String updatedBy;

    // Persist equal Create
    @PrePersist
    public void handleCreateAt() {
        // this.setCreatedBy(
        // SecurityUtil.getCurrentUserLogin().isPresent() ?
        // SecurityUtil.getCurrentUserLogin().get() : null);
        this.setCreatedBy(
                "le nam create");
        this.setCreatedAt(Instant.now());
    }

    @PreUpdate
    public void handleUpdatedBy() {
        // this.setUpdatedBy(
        // SecurityUtil.getCurrentUserLogin().isPresent() ?
        // SecurityUtil.getCurrentUserLogin().get() : null);

        this.setUpdatedBy(
                "le nam update");
        this.setUpdatedAt(Instant.now());
    }

}
