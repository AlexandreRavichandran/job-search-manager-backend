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

class MonsterScrapperTest {

    private final MonsterScrapper monsterScrapper = new MonsterScrapper();
    private Document document;

    @BeforeEach
    void findMonsterDocument() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/scrapperdocument/MonsterScrapperTestDocument.txt");
        this.document = Jsoup.parse(IOUtils.toString(fileInputStream, StandardCharsets.UTF_8));
    }

    @Test
    void whenGivenMonsterHTMLDocumentShouldReturnArrayOfDatas() {

        HashMap<String, String> testDatas = new HashMap<>();

        String testTitle = document.selectFirst(".JobViewTitle").text();
        String testDescription = document.selectFirst("#jobview-container").text();
        String testCompanyName = document.selectFirst(".crFatx").text();

        testDatas.put("title", testTitle);
        testDatas.put("description", testDescription);
        testDatas.put("companyName", testCompanyName);

        assertEquals("ALTERNANT CHARGÉ DE MISSION QUALITÉ - H/F", testDatas.get("title"));
        assertEquals("DÉTAILS DU POSTE TYPE DE CONTRAT Temps plein ADRESSE Courbevoie, IDF DATE DE PUBLICATION Il y a 2 jours Promu par TORANN-FRANCE Description Société Rejoindre TORANN-FRANCE, c’est faire partie d’une entreprise indépendante, leader sur le marché de la sécurité privée, où plus de 2 500 collaborateurs et collaboratrices experts de leurs domaines, grandissent ensemble pour contribuer au développement de l’entreprise et instaurer une véritable culture sécurité dans leur périmètre d’intervention. Mission Dans le cadre du renforcement de notre service QSE, nous recrutons : un Chargé de mission Qualité - H/F en contrat d'alternance basé à Courbevoie (92) Placé sous l'autorité du Responsable QSE qui sera également votre Maître d’Apprentissage, vous aurez pour mission principale d'aider à mettre en place le système de management de la qualité au sein de notre groupe. A ce titre, vous: Contribuez à la formalisation du Manuel Qualité et du SMQ; Rédigez les fiches de processus; Accompagnez les services dans la construction des documents métiers associés; Suivez la réalisation des actions dans le cadre de la mise en place de la démarche; Participez à la préparation et au suivi des audits internes; Mettez à jour les tableaux de suivi des indicateurs. Des déplacements ponctuels sont à prévoir sur toute la France. Profil De formation Bac+3 dans le domaine QSE, vous préparez un diplôme de type M1/M2 en alternance dans le domaine de la Qualité. Vous avez idéalement réalisé un premier stage ou occupé un premier emploi en alternance dans ce domaine. Doté d'un excellent relationnel, votre capacité à travailler en équipe, vos qualités rédactionnelles et votre rigueur seront des atouts majeurs pour réussir dans cette fonction. Vous savez exploiter l'ensemble des outils du pack Office MS (Word, Excel, Powerpoint, ...). À propos de la société TORANN-FRANCE Connue et reconnue pour notre expertise métier, notre qualité de service, notre loyauté et nos valeurs humaines, TORANN-FRANCE est un acteur majeur de la sécurité des biens et des personnes au service des entreprises françaises depuis plus de 30 ans. Nous rejoindre, c’est faire partie d’une entreprise familiale, indépendante, où plus de 2 500 collaborateurs et collaboratrices experts de leurs domaines, grandissent ensemble pour contribuer au développement de l’entreprise et instaurer une véritable culture sécurité dans leur périmètre d’intervention. TAILLE DE LA SOCIÉTÉ 2000 à 2499 salariés SECTEUR Other/Not Classified SITE WEB https://www.torann-france.fr/", testDatas.get("description"));
        assertEquals("TORANN-FRANCE", testDatas.get("companyName"));

    }
}