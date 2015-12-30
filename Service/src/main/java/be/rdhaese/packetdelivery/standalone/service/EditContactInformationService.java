package be.rdhaese.packetdelivery.standalone.service;


import be.rdhaese.packetdelivery.dto.ContactDetailsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created on 27/12/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class EditContactInformationService {

    private static final String GET_URI = "http://localhost:8080/contact-information/get";
    private static final String POST_URI = "http://localhost:8080/contact-information/post";

    public ContactDetailsDTO get() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ContactDetailsDTO> response = restTemplate.getForEntity(GET_URI, ContactDetailsDTO.class);
        return response.getBody();
    }

    public boolean save(ContactDetailsDTO contactDetailsDTO) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(POST_URI, contactDetailsDTO, boolean.class);
    }
}
