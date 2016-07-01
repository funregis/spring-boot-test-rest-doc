package com.altari.spring.ws.documentation;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.altari.spring.ws.documentation.CommonDescriptor.defaultIdTecField;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

import java.util.Optional;

/**
 * Description des champs communs pour la documentation
 * @Régis LIMARE
 *
 */
public interface ClientDescriptor {
    public static FieldDescriptor[] simplePaysDescriptor = new FieldDescriptor[] {
            defaultIdTecField(Optional.empty()),
            fieldWithPath("libelle").type(JsonFieldType.STRING).description("Libellé du pays")
    };
    public static FieldDescriptor[] paysDescriptor = new FieldDescriptor[] {
            defaultIdTecField(Optional.empty()),
            fieldWithPath("libelle").type(JsonFieldType.STRING).description("Libellé du pays"),
            fieldWithPath("nationalite").type(JsonFieldType.STRING).description("Nationalité")
    };
    public static FieldDescriptor[] adresseSimpleDescriptor = new FieldDescriptor[] {
            defaultIdTecField(Optional.empty()),
            fieldWithPath("rue").type(JsonFieldType.STRING).description("Rue (avec le numéro)"),
            fieldWithPath("ville").type(JsonFieldType.STRING).description("Ville"),
            fieldWithPath("codePostal").type(JsonFieldType.STRING).description("Code postal"),
            fieldWithPath("lieuNaissance").type(JsonFieldType.STRING).description("Lieu de naissance ( Ville + Code postal )"),
            fieldWithPath("pays.libelle").type(JsonFieldType.STRING).description("Libellé du pays") };
    
    public static  FieldDescriptor[] adresseDescriptor = new FieldDescriptor[] {
            defaultIdTecField(Optional.empty()),
            fieldWithPath("rue").type(JsonFieldType.STRING).description("Rue (avec le numéro)"),
            fieldWithPath("ville").type(JsonFieldType.STRING).description("Ville"),
            fieldWithPath("codePostal").type(JsonFieldType.STRING).description("Code postal"),
            fieldWithPath("codePostaln").type(JsonFieldType.STRING).description("Lieu de naissance ( Ville + Code postal )"),
            fieldWithPath("villen").type(JsonFieldType.STRING).description("Ville de naissance"),
            };
    public static  FieldDescriptor[] newAdresseDescriptor = new FieldDescriptor[] {
            fieldWithPath("rue").type(JsonFieldType.STRING).description("Rue (avec le numéro)"),
            fieldWithPath("ville").type(JsonFieldType.STRING).description("Ville"),
            fieldWithPath("codePostal").type(JsonFieldType.STRING).description("Code postal"),
            fieldWithPath("codePostaln").type(JsonFieldType.STRING).description("Lieu de naissance ( Ville + Code postal )"),
            fieldWithPath("villen").type(JsonFieldType.STRING).description("Ville de naissance"),
            fieldWithPath("pays").type(JsonFieldType.OBJECT).description("Pays"),
            fieldWithPath("paysn").type(JsonFieldType.OBJECT).description("Pays de naissance"),
            fieldWithPath("pays.idTec").type(JsonFieldType.NUMBER).description("Id technique d'un pays existant"),
            fieldWithPath("paysn.idTec").type(JsonFieldType.NUMBER).description("Id technique d'un pays de naissance existant")
            };
}
