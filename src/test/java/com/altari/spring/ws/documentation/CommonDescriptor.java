package com.altari.spring.ws.documentation;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.restdocs.headers.HeaderDescriptor;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;

/**
 * Element commun pour la documentation des service Rest
 * @Régis LIMARE
 *
 */
public class CommonDescriptor {
    // description de la pagination
    public final static List<FieldDescriptor> PAGINATION_FIELDS_DESCRIPTOR = Arrays.asList(
            fieldWithPath("last").type(JsonFieldType.BOOLEAN).description("Indicateur de fin de pagination"),
            fieldWithPath("first").type(JsonFieldType.BOOLEAN).description("Indicateur de début de pagination"),
            fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("Nombre de pages au total"),
            fieldWithPath("totalElements").type(JsonFieldType.NUMBER).description("Nombre total d'éléments"),
            fieldWithPath("size").type(JsonFieldType.NUMBER).description("Nombre d'éléments par page"),
            fieldWithPath("sort").type(JsonFieldType.STRING).description("Tri appliqué"),
            fieldWithPath("numberOfElements").type(JsonFieldType.NUMBER).description("Nombre d'éléments dans la page courante"),
            fieldWithPath("number").type(JsonFieldType.NUMBER).description("Numéro de la page courante"),
            fieldWithPath("content").type(JsonFieldType.ARRAY).description("Contient les données")
            );
    
    public static List<FieldDescriptor> basicDescriptor = Arrays.asList(
            defaultIdTecField(Optional.empty()),
            defaultLibelleField(Optional.empty()));
    /**
     * documentation du champ idTec
     * @param prefix
     * @return
     */
    public static FieldDescriptor defaultIdTecField(Optional<String> prefix){
        return fieldWithPath((prefix.isPresent()?prefix.get():"")+"idTec").type(JsonFieldType.NUMBER).description("Identifiant technique");
    }
    /**
     * documentation du champ libelle
     * @param prefix
     * @return
     */
    public static FieldDescriptor defaultLibelleField(Optional<String> prefix){
        return fieldWithPath((prefix.isPresent()?prefix.get():"")+"libelle").type(JsonFieldType.STRING).description("Libellé");
    }
    /**
     * ajoute la documentation liée à la pagination
     * @param fieldDescriptor
     * @return
     */
    public static List<FieldDescriptor> paginationFields(final FieldDescriptor... fieldDescriptor){
        List<FieldDescriptor> fieldDescriptorList=new ArrayList<>(Arrays.asList(fieldDescriptor));
        //ajout de la description des champs de pagination
        fieldDescriptorList.addAll(PAGINATION_FIELDS_DESCRIPTOR);
        return fieldDescriptorList;
    }
    /**
     * Description de la variable chemin {id}
     */
    public static ParameterDescriptor defaultPathId=parameterWithName("id").description("Identifiant technique");
    
    public static HeaderDescriptor headerLocation=headerWithName("location").description("url de la resource Crée");
}
