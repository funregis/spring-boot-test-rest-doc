package com.altari.spring.ws.rest.client;

import static com.altari.spring.ws.documentation.CommonDescriptor.basicDescriptor;
import static com.altari.spring.ws.documentation.CommonDescriptor.defaultPathId;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

import com.altari.spring.ws.rest.AbstractRestTest;

/**
 * Classe testant/documentant la resource /api/civilites
 * 
 * @Régis LIMARE
 *
 */
public class TestCivilites extends AbstractRestTest{

    @Test
    public void findAll() throws Exception{
        this.mockMvc.perform(defaultGet("/api/civilites"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.*", hasSize(4)))
        .andExpect(jsonPath("$[0].idTec", equalTo(1)))
        .andExpect(jsonPath("$[0].libelle", equalTo("Inconnue")))
        .andDo(document(DOCUMENT, preprocessResponse(prettyPrint())
                , responseFields().andWithPrefix("[].",basicDescriptor)));
    }
    @Test
    public void findById() throws Exception{
        this.mockMvc.perform(defaultGet("/api/civilites/{id}",4L))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.idTec", equalTo(4)))
        .andExpect(jsonPath("$.libelle", equalTo("Monsieur")))
        .andDo(document(DOCUMENT, preprocessResponse(prettyPrint())
                ,pathParameters(defaultPathId)
                , responseFields(basicDescriptor)));
    }
}
