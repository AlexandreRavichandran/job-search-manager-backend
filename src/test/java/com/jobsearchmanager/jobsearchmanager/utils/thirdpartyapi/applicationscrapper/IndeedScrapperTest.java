package com.jobsearchmanager.jobsearchmanager.utils.thirdpartyapi.applicationscrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class IndeedScrapperTest {

    private final IndeedScrapper indeedScrapper = new IndeedScrapper();
    private Document document;

    @BeforeEach
    void findIndeedDocument() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/scrapperdocument/IndeedScrapperTestDocument.txt");
        this.document = Jsoup.parse(IOUtils.toString(fileInputStream, StandardCharsets.UTF_8));
    }

    @Test
    void whenGivenIndeedHTMLDocumentShouldReturnArrayOfDatas() {
        HashMap<String, String> testDatas = new HashMap<>();

        String testTitle = document.selectFirst(".jobsearch-JobInfoHeader-title").text();
        String testDescription = document.selectFirst("#jobDescriptionText").text();
        String testCompanyName = document.selectFirst(".jobsearch-CompanyAvatar-companyLink").text();

        testDatas.put("title", testTitle);
        testDatas.put("description", testDescription);
        testDatas.put("companyName", testCompanyName);

        assertEquals("Conseillèr(e) de vente bien être (H/F)", testDatas.get("title"));
        assertEquals("Comme J’aime, leader français de l’accompagnement personnalisé du rééquilibrage alimentaire à distance, crée son réseau de centres de soins Studio COMME J’AIME. Avec l’ambition d’être La référence sur le marchvé des soins et des produits Minceur / Bien-être, nous disposons actuellement de plus de 60 studios, et nous isons 200 nouvelles ouvertures d’ici 2025. Studio COMME J’AIME, est un réseau de centres de soins, spécialisé dans le coaching pour « Aider nos clients à mincir et à adopter les principes d’une vie saine et équilibrée ». Notre succès réside dans notre méthode globale (associant soins du corps + rééquilibrage alimentaire + coaching personnalisé), notre forte capacité à mailler le territoire français sur un marché en pleine expansion. Studio COMME J’AIME recrute un(e) Conseiller(e) de vente bien-être pour son centre situé à Clay-Souilly. En tant que Commercial(e) bien-être, vous proposez et savez vendre nos prestations adaptées à la clientèle Vous travaillez au sein d’une équipe de 2 à 3 personnes avec pour objectif commun, le développement du chiffre d’affaires, la fidélisation de la clientèle et le respect du concept et de l’image de marque. Vous appliquez les méthodes Studio COMME J’AIME pour développer et fidéliser la clientèle à travers la vente de programmes minceur (cures) et de produits minceur et bien-être Vous pratiquez les soins dans le respect du protocole et de la méthode en vigueur et participez à la bonne tenue du centre. Vous organisez et restructurer en permanence les linéaires de vente en fonction des stocks disponibles. Vous garantissez vos objectifs commerciaux et la dynamique commerciale du centre dans le respect de notre Concept. Vous garantissez un accueil irréprochable dans le respect de la marque et de la différence Studio COMME J’AIME et vous participez à la bonne tenue du centre. Profil recherché Doté(e) d’une expérience dans le domaine commercial, vous êtes reconnu pour vos qualités d’écoute, de communication et savez instaurer une relation de confiance. En outre, la relation commerciale vous motive, vous aimez convaincre. Vous avez le sens du commerce. Vous êtes très motivé(e) pour un nouveau challenge, vous aimez les défis et vous avez la culture du résultat. Vous êtes sensible et appréciez l’univers de la diététique et du bien-être. Vous aimez aider les autres Vous êtes dynamique, vous avez une bonne présentation, vous êtes rigoureux(se) et bien organisé(e), polyvalent(e) et disponible. Un minimum de maitrise d’Excel est incontournable Notre entreprise s'engage en faveur de la diversité et du handicap et à ce titre étudie toutes les candidatures à niveau d'exigence égal. Rémunération : Temps plein 35h : 1700 e brut Fixe + variables sur objectifs CA (déplafonné) Télétravail Non Type d'emploi : Temps plein, CDI Salaire : à partir de 1 700,00€ par mois Avantages : Participation au Transport Horaires : Travail en journée", testDatas.get("description"));
        assertEquals("Studio Comme J'aime", testDatas.get("companyName"));
    }
}