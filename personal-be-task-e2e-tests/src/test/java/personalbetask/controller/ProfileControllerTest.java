package personalbetask.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.sarayeva.personalbetask.PersonalBeTaskApplication;
import com.sarayeva.personalbetask.dto.ProfileRequestDto;
import com.sarayeva.personalbetask.dto.ProfileResponseDto;
import java.math.BigDecimal;
import java.net.URI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(classes = PersonalBeTaskApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProfileControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;
  private final String PROFILE_PATH = "http://localhost:%d/personal-project/v1/profile-management/profiles";


  @Test
  @Sql(scripts = {"classpath:schema.sql"})
  public void addProfile_success_returnProfileResponse() {
    ProfileRequestDto profileRequestDto = ProfileRequestDto.builder()
        .name("test name 2")
        .surname("test surname 2")
        .budget(BigDecimal.valueOf(1000))
        .build();
    ResponseEntity<ProfileResponseDto> responseEntity = this.restTemplate
        .postForEntity(URI.create(String.format(PROFILE_PATH, port)),
            profileRequestDto, ProfileResponseDto.class);
    assertEquals(201, responseEntity.getStatusCodeValue());
    assertNotNull(responseEntity.getBody());
    assertEquals(responseEntity.getBody().getName(), profileRequestDto.getName());
    assertEquals(responseEntity.getBody().getSurname(), profileRequestDto.getSurname());
    assertEquals(responseEntity.getBody().getBudget(), profileRequestDto.getBudget());
  }

  @Test
  @Sql(scripts = {"classpath:schema.sql"})
  public void retrieveProfile_success_returnProfileResponse() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("x-profile-id", "2");
    ResponseEntity<ProfileResponseDto> responseEntity = this.restTemplate
        .exchange(URI.create(String.format(PROFILE_PATH, port)),
            HttpMethod.GET, new HttpEntity<>(httpHeaders), ProfileResponseDto.class);
    assertEquals(200, responseEntity.getStatusCodeValue());
    assertNotNull(responseEntity.getBody());
    assertEquals(responseEntity.getBody().getName(), "test name");
    assertEquals(responseEntity.getBody().getSurname(), "test surname");
    assertEquals(responseEntity.getBody().getBudget(), BigDecimal.valueOf(1000));
  }

  @Test
  @Sql(scripts = {"classpath:schema.sql"})
  public void retrieveProfile_error_throwProfileNotfoundException() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("x-profile-id", "5");
    ResponseEntity<ProfileResponseDto> responseEntity = this.restTemplate
        .exchange(URI.create(String.format(PROFILE_PATH, port)),
            HttpMethod.GET, new HttpEntity<>(httpHeaders), ProfileResponseDto.class);
    assertEquals(404, responseEntity.getStatusCodeValue());
    assertNotNull(responseEntity.getBody());
  }

  @Test
  @Sql(scripts = {"classpath:schema.sql"})
  public void updateProfile_success_returnProfileResponse() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("x-profile-id", "2");
    ProfileRequestDto profileRequestDto = ProfileRequestDto.builder()
        .name("test name 2")
        .surname("test surname")
        .budget(BigDecimal.valueOf(1000))
        .build();
    HttpEntity<ProfileRequestDto> requestEntity =
        new HttpEntity<>(profileRequestDto, httpHeaders);
    ResponseEntity<ProfileResponseDto> responseEntity = this.restTemplate
        .exchange(URI.create(String.format(PROFILE_PATH, port)), HttpMethod.PUT, requestEntity,
            ProfileResponseDto.class);
    assertEquals(201, responseEntity.getStatusCodeValue());
    assertNotNull(responseEntity.getBody());
    assertEquals(responseEntity.getBody().getName(), profileRequestDto.getName());
    assertEquals(responseEntity.getBody().getSurname(), profileRequestDto.getSurname());
    assertEquals(responseEntity.getBody().getBudget(), profileRequestDto.getBudget());
  }
}
