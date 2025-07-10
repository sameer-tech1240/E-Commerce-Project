package com.quickCommerce.implement;

import com.quickCommerce.deliveryentity.DeliveryBoy;
import com.quickCommerce.dto.LoginDTO;
import com.quickCommerce.repo.DeliveryBoyRepository;
import com.quickCommerce.service.LoginService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    private final DeliveryBoyRepository repository;

    public LoginServiceImpl(DeliveryBoyRepository repository) {
        this.repository = repository;
    }

    @Override
    public String login(LoginDTO loginDTO) {
        Optional<DeliveryBoy> optional = repository.findByEmail(loginDTO.getEmail());
        if (optional.isEmpty()) return "Invalid email!";

        DeliveryBoy boy = optional.get();
        if (!boy.getPassword().equals(loginDTO.getPassword())) return "Wrong password!";

        // ✅ Set location
        boy.setLatitude(loginDTO.getLatitude());
        boy.setLongitude(loginDTO.getLongitude());
        repository.save(boy);

        return "Login successful!";
    }

}
