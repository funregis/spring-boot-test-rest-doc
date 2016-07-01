package com.altari.spring.ws.documentation;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.util.FileSystemUtils;

import com.altari.spring.ws.rest.client.TestAdresses;
import com.altari.spring.ws.rest.client.TestCivilites;
import com.altari.spring.ws.rest.client.TestPays;

/**
 * Classe permettant de lancer tout les tests et surtout de nettoyer le répertoire 
 * ou sont générer les snippet avant de lancer les test et la générération de la doc
 * @Régis LIMARE
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ TestAdresses.class, TestCivilites.class,TestPays.class })
public class FullTestAndDocumentation {
    @BeforeClass 
    public static void setUpClass() {      
        FileSystemUtils.deleteRecursively(new File("target/generated-snippets"));
    }
}
