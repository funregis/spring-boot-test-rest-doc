package com.altari.spring.ws.rest.client;

import static com.altari.spring.ws.documentation.ClientDescriptor.adresseSimpleDescriptor;
import static com.altari.spring.ws.documentation.ClientDescriptor.newAdresseDescriptor;
import static com.altari.spring.ws.documentation.CommonDescriptor.defaultPathId;
import static com.altari.spring.ws.documentation.CommonDescriptor.headerLocation;
import static com.altari.spring.ws.documentation.CommonDescriptor.paginationFields;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import com.altari.spring.ws.domain.client.Adresse;
import com.altari.spring.ws.rest.AbstractRestTest;

/**
 * Classe testant la resource /api/adresses
 * 
 * @Régis LIMARE
 *
 */
public class TestAdresses extends AbstractRestTest {

    @Test
    public void findByCriteria() throws Exception {

        Adresse criteria = new Adresse();
        criteria.setCodePostal("69");
        criteria.setRue("visi");

        this.mockMvc.perform(defaultPost("/api/adresses/search/find-by-criteria")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(criteria)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPages", equalTo(2)))
                .andExpect(jsonPath("$.totalElements", equalTo(24)))
                .andExpect(jsonPath("$.size", equalTo(20)))
                .andExpect(jsonPath("$.codePostaln").doesNotExist())
                .andExpect(jsonPath("$.villen").doesNotExist())
                .andExpect(jsonPath("$.paysn").doesNotExist())
                .andDo(document(DOCUMENT, preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("rue").optional().type(JsonFieldType.STRING).description("critère de recherche sur la rue (peut être partielle)"),
                                fieldWithPath("ville").optional().type(JsonFieldType.STRING).description("critère de recherche sur la ville (peut être partielle)"),
                                fieldWithPath("codePostal").optional().type(JsonFieldType.STRING).description("critère de recherche sur le code postal (peut être partiel)"),
                                fieldWithPath("pays.libelle").optional().type(JsonFieldType.STRING).description("critère de recherche sur le pays (peut être partiel)")),
                        responseFields(
                                paginationFields())
                                        .andWithPrefix("content[].", adresseSimpleDescriptor)));
    }

    @Test
    public void findById() throws Exception {

        this.mockMvc.perform(defaultGet("/api/adresses/{id}", 2L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codePostaln").doesNotExist())
                .andExpect(jsonPath("$.villen").doesNotExist())
                .andExpect(jsonPath("$.paysn").doesNotExist())
                .andDo(document(DOCUMENT, preprocessResponse(prettyPrint()),
                        pathParameters(defaultPathId),
                        responseFields(adresseSimpleDescriptor)));
    }

    @Test
    public void create() throws Exception {

        String adrStr;
        try (InputStream adresseInputStream = this.getClass().getClassLoader().getResource("com/natixis/spring/ws/rest/client/newAdresse.txt").openStream();
                Scanner scan = new Scanner(adresseInputStream, "utf-8");) {
            scan.useDelimiter("\\Z");
            adrStr = scan.next();
            scan.close();
        }

        String location=this.mockMvc.perform(defaultPost("/api/adresses")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(adrStr))
                .andExpect(status().isCreated())
                .andExpect(header().string("location",startsWith("http")))
                .andDo(document(DOCUMENT, preprocessRequest(prettyPrint()),
                        requestFields(newAdresseDescriptor)
                        ,responseHeaders(headerLocation)))
                .andReturn().getResponse().getHeader("location");
        
      //vérification que la ressource a bien été créée
      this.mockMvc.perform(defaultGet(location))
        .andExpect(status().isOk());
    }

    @Test
    public void deleteAdresse() throws Exception {
        this.mockMvc.perform(delete("/api/adresses/{id}", 1L))
                .andExpect(status().isNoContent())
                .andDo(document(DOCUMENT,
                        pathParameters(defaultPathId)));
        
        //vérification que la ressource a bien été supprimées
        this.mockMvc.perform(defaultGet("/api/adresses/{id}", 1L))
        .andExpect(status().isNotFound());
    }
}
