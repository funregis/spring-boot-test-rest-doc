spring-boot-test-rest-doc
===================
L'objectif de ce projet est de montrer ce qu'il est possible de faire en terme de test et de documentation de service rest, le tout basé sur spring-boot et toute la stack spring. Sous la forme d'une application web autonome ( le serveur d'application est inclus).
Ce projet met en avant Spring Doc rest , qui prone la documentation des services Rest lors de l'écriture des tests.

La démo n'est pas basée sur du Hateoas , mais sur des services Rest standards ( flux json et non hal+json) qui pour le moment est le plus commun.  

Ce projet peut aussi permettre de démarrer plus rapidement sur la création d'un projet backend basé sur des services rest autour de spring-boot.

----------
### Sommaire
[TOC]

----------
Pré-requis
-------------------
- **JDK1.8**
- **maven 3+**
- **eclipse**
- **un plugin de type jsonviewer d'installé dans le navigateur**
- **un plugin permettant d'appeler des services rest , par exemple restclient d'installé dans le navigateur**

----------
Contenu
-------------------
Le projet a les éléments suivants de préconfigurés :

> - **Base de donnée:**
> Bdd oracle de configurée

> - **Base in memory H2 pour les tests**

> - **Logger**
> Par défaut dans C:\Program Files (x86)\Developpements\logs
>    - spring-boot-test.log : généré lros de l'éxécution des tests
>    - spring-boot.log : généré par l'application

> - **Actuators**  
> Les actuators de spring boot sont activés et permettent de consulter sous la forme de services Rest les métriques de l'application. 

>- **API sécurisé via des roles**
>- **mode debug**
>le mode debug ( `--debug`) permet de loggué les accès au cache ainsi que les requêtes SQL exécutées par le moteur de persistence.

>- **Live reload**
>l'application redémarre automatiquement en local pour prendre en compte les modifications.
 
----------

Lancement de l'application
-------------------
####  Dans eclipse
Créer une "Run configuration" en précisant comme **main class**  
`com.altari.spring.ws.application.Application` , puis dans l'onglet **Arguments** mettre `-Dspring.profiles.active=dev` dans le champ **VM arguments**
> le profil dev permet de définir un utilisateur admin/admin permettant d'accèder aux services rest. (sans profil les url sont protégées par des roles et aucun utilisateur n'est défini, car il y a plusieurs moyen de faires , si l'application est déployée sous forme d'un jar (autonome) ou d'un war (sur un jbos par exemple )

>il est possible d'ajouter `--debug` dans le champ **Program arguments** afin d'activer les traces complètes

#### En dehors d'eclipse
Après avoir construit le livrable ( en suivant le chapitre **Construction du jar autonome**) , se postionner dans le répertoire target et lancer la commande suivante `java -jar -Dspring.profiles.active=dev aws-ws-spring-0.0.1-SNAPSHOT.jar`

----------

Construction du jar autonome ( embarque un tomcat)
-------------------
Depuis une commande maven il suffit de lancer `mvn clean package -Dmaven.clean.failOnError=false` à la racine du projet.

Ceci va générer un jar autonome dans le répertoire **target** du projet , qui va aussi contenir la documentation des services Rest sous la forme de pages html statiques incluses dans le jar.

----------
Urls , Démo
-------------------

Une fois l'application démarrée , elle est accessible dans le navigateur sur l'url http://localhost:8080 .

Le menu permet :

-  de tester les actuators via le menu déroulant **Monitoring**

-  d'acceder à la documentation qui a été générée, via le lien **DOC APi**

Les urls des service Rest sont indiquées dans la doc.
Elles sont de la forme `http://localhost:8080/api/<resource>`

----------
Documentation des services REST
-------------------

Dans cette application démo , seule les ressources `/api/adresses` , `/api/pays`,`/api/civilites` sont exposées, testées et documentées. 

La doc offcielle ne traite que d'exemples simples , la démo permet d'aller plus loin et se base sur des exemples plus concrets.

- La classe Junit orchestrant les test est **FullTestAndDocumentation.java**
- La documentation est situé dans **src\main\asciidoc\api** , la documentation s'appuie sur les snippets générés par les tests
- les snippets générés sont dans **target\generated-snippets**
- la documentation finale est générée dans **target\generated-docs**

----------
Concepts abordés dans l'application
-------------------
- service rest avec pagination , voir **AdressesResource.java**
- cache applicatif , voir **CachingSetup.java** et **ehcache.xml** 
- base de donnée in memory H2 pour les tests , voir **src\test\resources\config\application.yml** et **data-h2.sql**
- gestion des exceptions via des handler , voir **RestErrorHandlers.java**
- utilisation des JsonView et customisation de la serialisation pour gérér la pagination de spring-data avec les jsonview , voir **JacksonAdapter.java** et **Views.java**
- Test via spring-mock + Junit et documentation via spring rest , voir **AbstractRestTest.java** et **Test*.java**
- Prolfile spring pour **dev** et **test** , voir **WebSecurityConfig.java**
- Generation des snippets de description des service Rest , puis construction de la doc au format Html, puis packaging , voir **pom.xml**
