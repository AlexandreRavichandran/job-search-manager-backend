package com.jobsearchmanager.jobsearchmanager.utils.thirdpartyapi.applicationscrapper;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PoleEmploiScrapperTest {

    private final PoleEmploiScrapper poleEmploiScrapper = new PoleEmploiScrapper();
    private Document document;

    @BeforeEach
    void findPoleEmploiDocument() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/scrapperdocument/PoleEmploiScrapperTestDocument.txt");
        this.document = Jsoup.parse(IOUtils.toString(fileInputStream, StandardCharsets.UTF_8));
    }

    @Test
    void whenGivenPoleEmploiHTMLDocumentShouldReturnArrayOfDatas() {

        HashMap<String, String> testDatas = new HashMap<>();

        String testTitle = document.selectFirst("#labelPopinDetails").text();
        String testDescription = document.selectFirst(".description").text();
        String testCompanyName = document.selectFirst(".t4.title").text();

        testDatas.put("title", testTitle);
        testDatas.put("description", testDescription);
        testDatas.put("companyName", testCompanyName);

        assertEquals("Animateur qualité F/H (H/F)", testDatas.get("title"));
        assertEquals("SYNERGIE MONTOIR DE BRETAGNE Nous recherchons pour l'un de nos clients spécialisé dans le secteur automobile, 3 animateurs(trices) qualité.S'assurer de la bonne application du référentiel par les équipes et apporter l'accompagnement nécessaire : o Participer de manière active aux routines quotidiennes et hebdomadaires de l'UEP o Valider la polyvalence des équipes de production o Accompagner les équipes dans la maitrise des outils qualité : QRQC, 5M, 5 pourquoi, l'auto-contrôle o Réaliser des audits de poste o Être acteur dans l'amélioration des procédures communes aux UEP Garantir l'efficience des chaînes de contrôle permettant d'assurer la conformité des produits fournis aux clients : o Améliorer l'efficience des contrôles pour en améliorer la robustesse et l'efficacité o Créer la documentation et les enregistrements pour les nouvelles gammes en partenariat avec les équipes méthodes S'assurer de l'application des procédures de gestion des non-conformités : Soutenir les équipes dans les analyses des non-conformités Communiquer les non-conformité fournisseur auprès du responsable qualité fournisseur Apporter le support méthodologique nécessaire au traitement efficace des faits techniques et des actions d'amélioration nécessaires. S'assurer de la validation des lots de véhicules avant la présentation au client o Assurer la communication des résultats des présentations véhicules vers les équipes de production o S'assurer de la mise en oeuvre des analyses et plans d'actions robustes pour supprimer de manière pérenne les écarts rencontrés lors des validations o Communiquer auprès du client, les plans d'actions mis oeuvre sur les écarts des présentations précédentes De formation de niveau bac+2/+4 en qualité ou équivalent - Une première expérience en Qualité en entreprise et dans le secteur industriel - Connaissance des outils qualité : o Résolution de problème QRQC/ 8D/ 5Pourquoi/ 5M... o Audit o Analyses de risques - Connaissance des logiciels de gestion internes ainsi que des outils bureautiques MS Office (Word, Excel, PowerPoint) - Savoir animer et communiquer - Être pédagogue, persuasif et à l'écoute - Être pragmatique et avoir une aptitude à échanger sur le terrain - Savoir travailler en équipe - Respect (de soi-même et des autres) - Être orienté client - Faire preuve d'esprit d'analyse et de synthèse - Rigueur et sérieuxVos avantages Synergie: +10% IFM + 10% CP, CSE Synergie, Prime Participation + CET à 6%, Aides et Services (Mutuelle - Logement - Garde d'enfants...)", testDatas.get("description"));
        assertEquals("SYNERGIE", testDatas.get("companyName"));

    }

}