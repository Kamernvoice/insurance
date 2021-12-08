package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.entity.Offer;
import com.example.springsecurityjwt.entity.Contract;
import com.example.springsecurityjwt.dto.UserDto;
import com.example.springsecurityjwt.entity.Licence;
import com.example.springsecurityjwt.entity.Passport;
import com.example.springsecurityjwt.repository.*;
import com.example.springsecurityjwt.service.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springsecurityjwt.entity.User;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class ProfileController {

    private final UserRepository userRepository;
    private final OfferRepository offerRepository;
    private final ContractRepository contractRepository;
    private final LicenceRepository licenceRepository;
    private final PassportRepository passportRepository;
    private final UserConverter userConverter;

    @Autowired
    public ProfileController(UserRepository userRepository,
                             LicenceRepository licenceRepository,
                             PassportRepository passportRepository,
                             UserConverter userConverter,
                             OfferRepository offerRepository,
                             ContractRepository contractRepository) {
        this.userRepository = userRepository;
        this.licenceRepository = licenceRepository;
        this.passportRepository = passportRepository;
        this.userConverter = userConverter;
        this.offerRepository = offerRepository;
        this.contractRepository = contractRepository;
    }

    @GetMapping(value = "/profile")
    public ResponseEntity<?> getProfile(Principal principal) {
        User user = userRepository.findByLogin(principal.getName());
        UserDto userDto = userConverter.fromUserToUserDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping(value = "/insurer/licence")
    public ResponseEntity<?> create(@RequestBody Licence licence, Principal loggedUser) {
        User insurer = userRepository.findByLogin(loggedUser.getName());
        licence.setInsurer(insurer);
        licenceRepository.save(licence);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/client/passport")
    public ResponseEntity<?> create(@RequestBody Passport passport, Principal loggedUser) {
        User client = userRepository.findByLogin(loggedUser.getName());
        passport.setClient(client);
        passportRepository.save(passport);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/insurer/licence")
    public ResponseEntity<Licence> findLicenceByInsurer(Principal loggedUser) {
        User insurer = userRepository.findByLogin(loggedUser.getName());
        final Licence licence = licenceRepository.findLicenceByInsurer(insurer);

        return licence != null
                ? new ResponseEntity<>(licence, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/client/passport")
    public ResponseEntity<Passport> findPassportByClient(Principal loggedUser) {
        User client = userRepository.findByLogin(loggedUser.getName());
        final Passport passport = passportRepository.findPassportByClient(client);

        return passport != null
                ? new ResponseEntity<>(passport, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/insurer/offers")
    public ResponseEntity<?> createOffer(@RequestBody Offer offer, Principal loggedUser) {
        User insurer = userRepository.findByLogin(loggedUser.getName());
        offer.setInsurer(insurer);
        offerRepository.save(offer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/client/contracts")
    public ResponseEntity<?> createContract(@RequestBody Contract contract, Principal loggedUser) {
        User client = userRepository.findByLogin(loggedUser.getName());
        contract.setClient(client);
        contractRepository.save(contract);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/insurer/offers")
    public ResponseEntity<List<Offer>> findOffersByInsurer(Principal loggedUser) {
        User insurer = userRepository.findByLogin(loggedUser.getName());
        final List<Offer> offers = offerRepository.findAllByInsurer(insurer);

        return offers != null &&  !offers.isEmpty()
                ? new ResponseEntity<>(offers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/client/contracts")
    public ResponseEntity<List<Contract>> findContractsByClient(final Principal loggedUser) {
        User client = userRepository.findByLogin(loggedUser.getName());
        final List<Contract> contracts = contractRepository.findAllByClient(client);

        return contracts != null &&  !contracts.isEmpty()
                ? new ResponseEntity<>(contracts, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/insurer/{id}/offers")
    public ResponseEntity<List<Offer>> findOffersByInsurerId(@PathVariable(name = "id") int id) {
        User insurer = userRepository.findUserById(id);
        final List<Offer> offers = offerRepository.findAllByInsurer(insurer);

        return offers != null &&  !offers.isEmpty()
                ? new ResponseEntity<>(offers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/client/{id}/contracts")
    public ResponseEntity<List<Contract>> findContractsByClientId(@PathVariable(name = "id") int id) {
        User client = userRepository.findUserById(id);
        final List<Contract> contracts = contractRepository.findAllByClient(client);

        return contracts != null &&  !contracts.isEmpty()
                ? new ResponseEntity<>(contracts, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/insurer/{id}")
    public ResponseEntity<UserDto> getInsurerById(@PathVariable(name = "id") int id) {
        final UserDto userDTO = userConverter.fromUserToUserDto(userRepository.findUserById(id));

        return userDTO != null
                ? new ResponseEntity<>(userDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/client/{id}")
    public ResponseEntity<UserDto> getClientById(@PathVariable(name = "id") int id) {
        final UserDto userDTO = userConverter.fromUserToUserDto(userRepository.findUserById(id));

        return userDTO != null
                ? new ResponseEntity<>(userDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/insurer/{id}")
    public ResponseEntity<?> updateInsurer(@PathVariable(name = "id") int id, @RequestBody User user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            final User updated = userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping(value = "/client/{id}")
    public ResponseEntity<?> updateClient(@PathVariable(name = "id") int id, @RequestBody User user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            final User updated = userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/insurer/{id}")
    public ResponseEntity<?> deleteInsurer(@PathVariable(name = "id") int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/client/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable(name = "id") int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
