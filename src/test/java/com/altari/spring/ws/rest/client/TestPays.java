package com.altari.spring.ws.rest.client;

import static com.altari.spring.ws.documentation.ClientDescriptor.paysDescriptor;
import static com.altari.spring.ws.documentation.CommonDescriptor.defaultPathId;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.restdocs.payload.JsonFieldType;

import com.altari.spring.ws.rest.AbstractRestTest;

/**
 * Classe testant/documentant la resource /api/pays
 * 
 * @Régis LIMARE
 *
 */
public class TestPays extends AbstractRestTest {
        
    @Test
    public void findPaysByLibelle() throws Exception {
        this.mockMvc.perform(defaultGet("/api/pays/search/find-by-libelle")
                .param("libelle", "France"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.libelle", equalTo("France")))
                .andDo(document(DOCUMENT, preprocessResponse(prettyPrint()), 
                        responseFields(paysDescriptor)));
    }

    @Test
    public void findAll() throws Exception {
        this.mockMvc.perform(defaultGet("/api/pays"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(19)))
                .andDo(document(DOCUMENT, preprocessResponse(prettyPrint()), 
                        responseFields().andWithPrefix("[].", paysDescriptor)));
    }

    @Test
    public void findById() throws Exception {
        this.mockMvc.perform(defaultGet("/api/pays/{id}",1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.libelle", equalTo("France")))
                .andExpect(jsonPath("$.nationalite", equalTo("Française")))
                .andDo(document(DOCUMENT, preprocessResponse(prettyPrint()),
                        pathParameters(defaultPathId)
                        , responseFields(paysDescriptor)));
    }

    @Test
    public void findNationaliteByPartialNationnalite() throws Exception {
        this.mockMvc.perform(defaultGet("/api/pays/search/find-nationalite-by-partial-nationalite")
                .param("partial", "Al"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$[0]", equalTo("Allemande")))
                .andExpect(jsonPath("$[1]", equalTo("Algérienne")))
                .andDo(document(DOCUMENT, preprocessResponse(prettyPrint()), responseFields(
                        fieldWithPath("[]").type(JsonFieldType.STRING).description("Nationalité"))));
    }
}
