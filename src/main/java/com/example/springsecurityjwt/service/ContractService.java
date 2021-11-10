package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.dto.ContractDto;
import com.example.springsecurityjwt.entity.Contract;
import com.example.springsecurityjwt.repository.ContractRepository;
import com.example.springsecurityjwt.repository.OfferRepository;
import com.example.springsecurityjwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContractService {

    private ContractRepository contractRepository;
    private UserRepository userRepository;
    private OfferRepository offerRepository;

    public Contract fromContractDtoToContract(ContractDto contractDto) {
        Contract contract = new Contract();
        contract.setId(contractDto.getId());
        contract.setContractDate(contractDto.getContractDate());
        contract.setClient(userRepository.findUserById(contractDto.getClientId()));
        contract.setOffer(offerRepository.findOfferById(contractDto.getOfferId()));
        contract.setInsurerConfirmContractStatus(contractDto.getInsurerConfirmContractStatus());
        contract.setInsurerConfirmPaymentStatus(contractDto.getInsurerConfirmPaymentStatus());
        contract.setClientIncidentStatus(contractDto.getClientIncidentStatus());
        contract.setInsurerConfirmIncidentStatus(contractDto.getInsurerConfirmIncidentStatus());
        contract.setInsurerConfirmPaymentStatus(contractDto.getInsurerConfirmPaymentStatus());
        return contract;
    }

    public ContractDto fromContractToContractDto(Contract contract) {
        return ContractDto.builder()
                .id(contract.getId())
                .contractDate(contract.getContractDate())
                .clientId(contract.getClient().getId())
                .clientName(contract.getClient().getName())
                .offerId(contract.getOffer().getId())
                .insurerConfirmContractStatus(contract.getInsurerConfirmContractStatus())
                .insurerConfirmPaymentStatus(contract.getInsurerConfirmPaymentStatus())
                .clientIncidentStatus(contract.getClientIncidentStatus())
                .insurerConfirmIncidentStatus(contract.getInsurerConfirmIncidentStatus())
                .clientConfirmPaymentStatus(contract.getClientConfirmPaymentStatus())
                .build();
    }
}
