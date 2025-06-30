package com.quickCommerce.dto;
import com.quickCommerce.entity.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {
    private Long id;
    private String username;
    private String email;
    private String role;

    public AdminDTO(Admin admin) {
        this.id = admin.getId();
        this.username = admin.getUsername();
        this.email = admin.getEmail();
        this.role = admin.getRole();
    }

}

