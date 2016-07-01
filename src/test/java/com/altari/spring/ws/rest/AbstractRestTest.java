package com.altari.spring.ws.rest;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static com.jayway.restassured.config.EncoderConfig.encoderConfig;
import static com.jayway.restassured.config.DecoderConfig.decoderConfig;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.altari.spring.ws.application.Application;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;

/**
 * classe abstraite utilisée pour les tests
 * définit les élements communs aux tests
 * @Régis LIMARE
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest("server.port=0")
public abstract class AbstractRestTest {
    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
    
    @Value("${local.server.port}")
    protected int port;
    
    @Autowired
    protected WebApplicationContext context;

    @Autowired  
    protected  ObjectMapper objectMapper;
    
    protected  ObjectMapper requestMapper;
    
    protected MockMvc mockMvc;
    
    protected static final String DOCUMENT = "{class-name}/{method-name}";
    
    @Before
    public void setUp() {       
        RestAssured.port = port;
        RestAssured.config().encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));
        RestAssured.config().decoderConfig(decoderConfig().defaultContentCharset("UTF-8"));
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation)
                        .snippets().withEncoding("UTF-8")
                        .withAdditionalDefaults())
                .build();        
        
        this.requestMapper = new ObjectMapper();
    }
    
    protected MockHttpServletRequestBuilder defaultGet(String path,Object... urlVariables){
        return get(path,urlVariables).accept(MediaType.APPLICATION_JSON_UTF8);
    }
    protected MockHttpServletRequestBuilder defaultPost(String path){
        return post(path).accept(MediaType.APPLICATION_JSON_UTF8);
    }
}