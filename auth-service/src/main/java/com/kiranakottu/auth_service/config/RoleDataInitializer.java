package com.kiranakottu.auth_service.config;


import com.kiranakottu.auth_service.entity.Role;
import com.kiranakottu.auth_service.entity.User;
import com.kiranakottu.auth_service.repository.RoleRepository;
import com.kiranakottu.auth_service.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class RoleDataInitializer {

    // This class can be used to initialize default roles in the database if needed.
    @Bean
    CommandLineRunner initializeRoles(RoleRepository roleRepository,
                                      UserRepository userRepository,
                                      PasswordEncoder encoder) {
        // Implementation for initializing roles can be added here.
        return args -> {
            if (roleRepository.findByName("ADMIN") == null) {
                roleRepository.save(new Role(null, "ADMIN"));
            }
            if (roleRepository.findByName("CUSTOMER") == null) {
                roleRepository.save(new Role(null, "CUSTOMER"));
            }
            if (roleRepository.findByName("WORKER") == null) {
                roleRepository.save(new Role(null, "WORKER"));
            }

            // Seed first admin user (only if none exist)
            if (userRepository.count() == 0) {
                Role adminRole = roleRepository.findByName("ADMIN");

                User admin = new User();
                admin.setUsername("kiranaadmin");
                admin.setName("Kirana Admin");
                admin.setEmail("admin@kiranakottu.com");
                admin.setPassword(encoder.encode("adminkirana")); // ⚠️ change after first login
                admin.setRole(adminRole);

                userRepository.save(admin);
                System.out.println("✅ Default admin user created: admin@kiranakottu.com / admin123");
            }
        };
    }
}
