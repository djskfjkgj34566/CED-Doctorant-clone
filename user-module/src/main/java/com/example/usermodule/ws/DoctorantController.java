package com.example.usermodule.ws;

import com.example.usermodule.model.EmailDetails;
import com.example.usermodule.request.AuthenticationRequestDto;
import com.example.usermodule.dto.PasswordDto;
import com.example.usermodule.dto.PreinscriptionDto;
import com.example.usermodule.jwt.JwtUtils;
import com.example.usermodule.model.CustomUserDetails;
import com.example.usermodule.model.Doctorant;
import com.example.usermodule.response.AuthenticationResponse;
import com.example.usermodule.service.AuthenticationProviderService;
import com.example.usermodule.service.DoctorantService;
import com.example.usermodule.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/ms-user/doctorant")
public class DoctorantController {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private DoctorantService service;
    @Autowired
    private AuthenticationProviderService providerService;
    @Autowired
    private EmailService emailService;

    @PostMapping("/admin/validate")
    public ResponseEntity<Doctorant> save(@RequestBody PreinscriptionDto preinscription) throws NoSuchAlgorithmException {
        Doctorant doctorant = service.save(preinscription);
        if(doctorant!=null) {
            return new ResponseEntity<>(doctorant, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(doctorant, HttpStatus.CONFLICT);
    }

    @PostMapping("/reset/password")
    public int sendPageUpdatePassword(@RequestBody String email) throws NoSuchAlgorithmException {
        return service.sendPageUpdatePassword(email);
    }

    @PostMapping("/admin/rejet")
    public int rejeter(@RequestBody PreinscriptionDto preinscription){
        return service.rejeter(preinscription);
    }

    @PostMapping(value = "/user/set", consumes = {"application/json"})
    public int setPassword(@RequestBody PasswordDto passwordDto){
        return service.setDoctorantPassword(passwordDto);
    }

    @PostMapping(value = "/user/update/passwd", consumes = {"application/json"})
    public int updatePassword(@RequestBody PasswordDto passwordDto){
        return service.updateDoctorantPassword(passwordDto);
    }

    @GetMapping("/user/{vkey}")
    public ResponseEntity<Doctorant> getByVkey(@PathVariable String vkey){
        Doctorant doctorant = service.getByVkey(vkey);
        if(doctorant!=null) return new ResponseEntity<>(doctorant, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authentication(@RequestBody AuthenticationRequestDto request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new AuthenticationResponse(
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getFullName(),
                jwt,
                roles));
    }

    @GetMapping("/")
    public List<Doctorant> findAll() {
        return service.findAll();
    }

    @GetMapping("/id/{id}")
    public Doctorant findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/get/count")
    public Long countData() {
        System.out.println(service.countData());
        return service.countData();
    }
    @GetMapping("/get/specialite/{id}")
    public String getSpecialite(@PathVariable Long id) {
        return service.getSpecialite(id);
    }
    @PostMapping("/sendSimpleMail")
    public String sendSimpleMail(@RequestBody List<EmailDetails> emailDetailsList)
    {
        String status = "";
        for (EmailDetails emailDetails: emailDetailsList) {
            status = emailService.SendSimpleMail(emailDetails);
            if (status.indexOf("Error") != -1)
            {
                break;
            }

        }
        return status;
    }
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@RequestBody List<EmailDetails> emailDetailsList)
    {
        String status = "";
        for (EmailDetails emailDetails: emailDetailsList) {
            status = emailService.SendMailWithAttachment(emailDetails);
            if (status.indexOf("Error") != -1)
            {
                break;
            }

        }
        return status;
    }
}
