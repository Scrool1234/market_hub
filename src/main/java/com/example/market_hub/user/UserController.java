package com.example.market_hub.user;

import com.example.market_hub.user.dto.UpdateUserDTO;
import com.example.market_hub.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UpdateUserDTO updateUserDTO) {
        return ResponseEntity.ok(userService.update(updateUserDTO));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getById(userId));
    }



}
