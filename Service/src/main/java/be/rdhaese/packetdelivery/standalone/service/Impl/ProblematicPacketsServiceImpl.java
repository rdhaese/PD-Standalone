package be.rdhaese.packetdelivery.standalone.service.Impl;

import be.rdhaese.packetdelivery.dto.DeliveryAddressDTO;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import be.rdhaese.packetdelivery.dto.RegionDTO;
import be.rdhaese.packetdelivery.standalone.service.AbstractService;
import be.rdhaese.packetdelivery.standalone.service.ProblematicPacketsService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created on 16/01/2016.
 *
 * @author Robin D'Haese
 */
@Service
public class ProblematicPacketsServiceImpl extends AbstractService implements ProblematicPacketsService {


    @Override
    public Collection<PacketDTO> getProblematicPackets() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PacketDTO[]> response = restTemplate.getForEntity(getUris().getProblematicPacketsPath(), PacketDTO[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public PacketDTO getProblematicPacket(String packetId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PacketDTO> response = restTemplate.getForEntity(getUris().getProblematicPacketPath(), PacketDTO.class, packetId);
        return response.getBody();
    }

    @Override
    public void reSend(String packetId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(getUris().getReSendProblematicPacketPath(), packetId, Boolean.class);
    }

    @Override
    public void returnToSender(String packetId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(getUris().getReturnToSenderProblematicPacketPath(), packetId, Boolean.class);
    }

    @Override
    public Collection<RegionDTO> getRegions(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RegionDTO[]> response = restTemplate.getForEntity(getUris().getAllRegionsPath(), RegionDTO[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public DeliveryAddressDTO getDeliveryAddress(String packetId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DeliveryAddressDTO> response = restTemplate.getForEntity(getUris().getProblematicPacketDeliveryAddressPath(), DeliveryAddressDTO.class, packetId);
        return response.getBody();
    }

    @Override
    public void saveDeliveryAddress(DeliveryAddressDTO deliveryAddressDTO) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(getUris().getSaveProblematicPacketDeliveryAddressPath(), deliveryAddressDTO, Boolean.class);
    }
}
