package cybercubebetask.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.sarayeva.cybercubebetask.CybercubeBeTaskApplication;
import com.sarayeva.cybercubebetask.constant.AnalysisType;
import com.sarayeva.cybercubebetask.dto.AnalysisRequestDto;
import com.sarayeva.cybercubebetask.dto.AnalysisResponseDto;
import java.net.URI;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(classes = CybercubeBeTaskApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AnalysisControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  private final String ANALYSIS_PATH = "http://localhost:%d/personal-project/v1/analysis-management/analyses";


  @Test
  @Sql(scripts = {"classpath:schema.sql"})
  public void addAnalysis_success_returnAnalysisResponse() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("x-profile-id", "3");
    AnalysisRequestDto analysisRequestDto = AnalysisRequestDto.builder().type(AnalysisType.FIRST)
        .viewers(List.of(2L)).hiddenInfo("test hidden info").build();
    HttpEntity<AnalysisRequestDto> requestEntity = new HttpEntity<>(analysisRequestDto,
        httpHeaders);
    ResponseEntity<AnalysisResponseDto> responseEntity = this.restTemplate.exchange(
        URI.create(String.format(ANALYSIS_PATH, port)), HttpMethod.POST, requestEntity,
        AnalysisResponseDto.class);
    assertEquals(201, responseEntity.getStatusCodeValue());
    assertNotNull(responseEntity.getBody());
    assertEquals(3, responseEntity.getBody().getOwner());
    assertEquals(analysisRequestDto.getType(), responseEntity.getBody().getType());
    assertEquals(analysisRequestDto.getHiddenInfo(), responseEntity.getBody().getHiddenInfo());
  }

  @Test
  @Sql(scripts = {"classpath:schema.sql"})
  public void addAnalysis_error_throwInsufficientBudget() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("x-profile-id", "2");
    AnalysisRequestDto analysisRequestDto = AnalysisRequestDto.builder().type(AnalysisType.SECOND)
        .viewers(List.of(3L)).hiddenInfo("test hidden info").build();
    HttpEntity<AnalysisRequestDto> requestEntity = new HttpEntity<>(analysisRequestDto,
        httpHeaders);
    ResponseEntity<AnalysisResponseDto> responseEntity = this.restTemplate.exchange(
        URI.create(String.format(ANALYSIS_PATH, port)), HttpMethod.POST, requestEntity,
        AnalysisResponseDto.class);
    assertEquals(400, responseEntity.getStatusCode().value());
  }

  @Test
  @Sql(scripts = {"classpath:schema.sql"})
  public void addAnalysis_error_throwViewerNotFoundException() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("x-profile-id", "2");
    AnalysisRequestDto analysisRequestDto = AnalysisRequestDto.builder().type(AnalysisType.SECOND)
        .viewers(List.of(5L)).hiddenInfo("test hidden info").build();
    HttpEntity<AnalysisRequestDto> requestEntity = new HttpEntity<>(analysisRequestDto,
        httpHeaders);
    ResponseEntity<AnalysisResponseDto> responseEntity = this.restTemplate.exchange(
        URI.create(String.format(ANALYSIS_PATH, port)), HttpMethod.POST, requestEntity,
        AnalysisResponseDto.class);
    assertEquals(400, responseEntity.getStatusCode().value());
  }

  @Test
  @Sql(scripts = {"classpath:schema-with-analysis.sql"})
  public void retrieveAnalysis_success_returnAnalysisResponseWithHiddenInfo() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("x-profile-id", "2");
    ResponseEntity<AnalysisResponseDto> responseEntity = this.restTemplate.exchange(
        URI.create(String.format(ANALYSIS_PATH + "/2", port)), HttpMethod.GET,
        new HttpEntity<>(httpHeaders), AnalysisResponseDto.class);
    assertEquals(200, responseEntity.getStatusCode().value());
    assertNotNull(responseEntity.getBody());
    assertEquals(2, responseEntity.getBody().getOwner());
    assertEquals("test hidden info 3", responseEntity.getBody().getHiddenInfo());
    assertEquals(AnalysisType.FIRST, responseEntity.getBody().getType());
  }

  @Test
  @Sql(scripts = {"classpath:schema-with-analysis.sql"})
  public void retrieveAnalysis_error_throwAnalysisNotFoundException() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("x-profile-id", "2");
    ResponseEntity<AnalysisResponseDto> responseEntity = this.restTemplate.exchange(
        URI.create(String.format(ANALYSIS_PATH + "/5", port)), HttpMethod.GET,
        new HttpEntity<>(httpHeaders), AnalysisResponseDto.class);
    assertEquals(404, responseEntity.getStatusCode().value());
  }

  @Test
  @Sql(scripts = {"classpath:schema-with-analysis.sql"})
  public void retrieveAnalysis_success_returnAnalysisResponseWithoutHiddenInfo() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("x-profile-id", "2");
    ResponseEntity<AnalysisResponseDto> responseEntity = this.restTemplate.exchange(
        URI.create(String.format(ANALYSIS_PATH + "/3", port)), HttpMethod.GET,
        new HttpEntity<>(httpHeaders), AnalysisResponseDto.class);
    assertEquals(200, responseEntity.getStatusCode().value());
    assertNotNull(responseEntity.getBody());
    assertEquals(3, responseEntity.getBody().getOwner());
    assertNull(responseEntity.getBody().getHiddenInfo());
    assertEquals(AnalysisType.SECOND, responseEntity.getBody().getType());
  }

  @Test
  @Sql(scripts = {"classpath:schema-with-analysis.sql"})
  public void retrieveAnalyses_success_returnAnalysisResponseList() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("x-profile-id", "2");
    ResponseEntity<List<AnalysisResponseDto>> responseEntity = this.restTemplate.exchange(
        URI.create(String.format(ANALYSIS_PATH, port)), HttpMethod.GET,
        new HttpEntity<>(httpHeaders),
        new ParameterizedTypeReference<List<AnalysisResponseDto>>() {
        });
    assertEquals(200, responseEntity.getStatusCodeValue());
    assertNotNull(responseEntity.getBody());
    assertEquals(2, responseEntity.getBody().size());
  }
}
