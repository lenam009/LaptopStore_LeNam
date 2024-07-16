package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Response;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResCreateUserDTO {
    private long id;

    private String email;

    private String fullName;

    private String address;

    private String phone;

    private String avatar;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss a", timezone = "GMT+7")
    private Instant createdAt;

}
