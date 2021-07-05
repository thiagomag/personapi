package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static one.digitalinnovation.personapi.utils.PersonUtils.createFakeDTO;
import static one.digitalinnovation.personapi.utils.PersonUtils.createFakeEntity;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDtoThenReturnSavedMessage() {
        PersonDTO personDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();

        Mockito.when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        MessageResponseDTO expectedSucessMessage = MessageResponseDTO
                .builder()
                .message("Created person with ID " + expectedSavedPerson.getId())
                .build();

        MessageResponseDTO sucessMessage = personService.createPerson(personDTO);

        Assertions.assertEquals(expectedSucessMessage, sucessMessage);
    }

}
