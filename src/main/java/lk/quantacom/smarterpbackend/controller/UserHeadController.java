package lk.quantacom.smarterpbackend.controller;

import lk.quantacom.smarterpbackend.configuration.TokenProvider;
import lk.quantacom.smarterpbackend.dto.request.LoginUserRequest;
import lk.quantacom.smarterpbackend.dto.request.UserHeadRequest;
import lk.quantacom.smarterpbackend.dto.request.UserHeadUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.AuthTokenResponse;
import lk.quantacom.smarterpbackend.dto.response.UserHeadResponse;
import lk.quantacom.smarterpbackend.service.UserHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("User")
@RestController
@CrossOrigin
public class UserHeadController {

    @Autowired
    private UserHeadService userHeadService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginUserRequest loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = "Bearer " + jwtTokenUtil.generateToken(authentication);
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        //LoginResponse save = this.loggerService.save(loginUser.getLoginSaveRequest());
        return ResponseEntity.ok(new AuthTokenResponse(userHeadService.getByUn(user.getUsername()),token));
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<UserHeadResponse> save(@Valid @RequestBody UserHeadRequest request) {
        UserHeadResponse save = userHeadService.save(request);
        return ResponseEntity.ok(save);
    }

    @PutMapping
    public ResponseEntity<UserHeadResponse> update(@Valid @RequestBody UserHeadUpdateRequest request) {
        UserHeadResponse updated = userHeadService.update(request);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @GetMapping("{id}")
    public ResponseEntity<UserHeadResponse> getById(@PathVariable("id") @NotBlank Long id) {
        UserHeadResponse get = userHeadService.getById(id);

        if (get == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(get);
    }


    @GetMapping()
    public ResponseEntity<List<UserHeadResponse>> getAll() {
        List<UserHeadResponse> getall = userHeadService.getAll();
        return ResponseEntity.ok(getall);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") @NotBlank Long id) {
        int deleted = userHeadService.delete(id);
        if (deleted == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleted);
    }
}