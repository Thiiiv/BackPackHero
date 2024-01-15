#include <iostream>
#define NOMINMAX
#include <Windows.h>
#include <cstdlib>

using namespace std;


string retour()
{
    string reponse;
    while(reponse != "C" && reponse != "S" && reponse != "Q"){
    cout << "Choisissez une des trois lettres valides s'il vous plait !" << endl;
    cin >> reponse;
    }
    return reponse;
}
string entree_jeu(string reponse = "")
{
    cout << "Bienvenue dans FunGames !" << endl
    << "Nous vous proposons de selectionner un de ces deux jeux en tapant l'initiale du jeu : "
    << endl << "- Chifoumi (C)" << endl << "- Snake (S)" << endl << endl << "Quitter (Q)" << endl;
    cin >> reponse;

    return reponse;
}

void jeu_contre_IA()
{
    cout << "Vous jouez contre l'IA !" << endl;
    cout
            << "|----------------------------------------------------------------------------------------|"<< endl
            << "| Voici le jeu du pierre-feuille-ciseaux!                                                |"<< endl
            << "| Les règles sont simples :                                                              |"<< endl
            << "| Le joueur choisi son objet entre la pierre, la feuille et les ciseaux.                 |"<< endl
            << "| La pierre bat les ciseaux, les ciseaux battent la feuille et la feuille bat la pierre. |"<< endl
            << "| Celui qui remporte le tour gagne un point.                                             |"<< endl
            << "| Le premier à trois points remporte la partie.                                          |"<< endl
            << "|----------------------------------------------------------------------------------------|"<< endl;

    string joueur;
    string IA = "Barman";
    string pierre = "P";
    string feuille = "F";
    string ciseaux = "C";
    string choix_pfc_joueur;
    string choix_IA;
    int point_joueur = 0;
    int point_IA = 0;
    int compteur = 0;
    string pfc[3] = {pierre, feuille, ciseaux};
    string reponse = "O";

    cout << "Rentre ton pseudo : ";
    cin >> joueur;
    cout << "La partie peut commencer !" << endl;

    while(reponse == "O")
    {
        while(point_joueur < 3 && point_IA < 3)
        {
            compteur += 1;
            cout << endl << "Tour " << compteur << endl;
            cout << joueur << " appuye sur 'P' pour jouer la pierre, sur 'F' pour jouer la feuille et sur 'C' pour jouer les ciseaux." << endl;
            cin >> choix_pfc_joueur;

            while(choix_pfc_joueur != pierre && choix_pfc_joueur != feuille && choix_pfc_joueur != ciseaux)
            {
                cout << joueur << " entrez une lettre valide." << endl;
                cin >> choix_pfc_joueur;
            }

            if(choix_pfc_joueur == pierre)
            {
                cout << "Tu as joué la pierre !" << endl;
            }
            else if(choix_pfc_joueur == feuille)
            {
                cout << "Tu as joué la feuille !" << endl;
            }
            else if(choix_pfc_joueur == ciseaux)
            {
                cout << "Tu as joué les ciseaux !" << endl;
            }
            Sleep(3000);

            cout << "A l'IA de choisir !" << endl;
            choix_IA = pfc[rand()%3];

            if(choix_IA == pierre)
            {
                cout << IA << " a joué la pierre !" << endl;
            }
            else if(choix_IA == feuille)
            {
                cout << IA << " a joué la feuille !" << endl;
            }
            else if(choix_IA == ciseaux)
            {
                cout << IA << " a joué les ciseaux !" << endl;
            }
            Sleep(2000);
            system("cls");

            if(choix_pfc_joueur == choix_IA)
            {
                cout << "Egalité" << endl;
                cout << "Aucun de vous deux n'a gagné de points." << endl
                     << joueur << " : " << point_joueur << endl
                     << IA << " : " << point_IA << endl;

            }

            else if(choix_pfc_joueur == pierre && choix_IA == feuille)
            {
                cout << "C'est "<< IA << " qui gagne !" << endl;
                point_IA += 1;
                cout << joueur << " : " << point_joueur << endl
                     << IA << " : " << point_IA << endl;
            }
            else if(choix_pfc_joueur == pierre && choix_IA == ciseaux)
            {
                cout << "C'est "<< joueur << " qui gagne !" << endl;
                point_joueur += 1;
                cout << joueur << " : " << point_joueur << endl
                     << IA << " : " << point_IA << endl;
            }
            else if(choix_pfc_joueur == feuille && choix_IA == pierre)
            {
                cout << "C'est "<< joueur << " qui gagne !" << endl;
                point_joueur += 1;
                cout << joueur << " : " << point_joueur << endl
                     << IA << " : " << point_IA << endl;
            }
            else if(choix_pfc_joueur == feuille && choix_IA == ciseaux)
            {
                cout << "C'est "<< IA << " qui gagne !" << endl;
                point_IA += 1;
                cout << joueur << " : " << point_joueur << endl
                     << IA << " : " << point_IA << endl;
            }
            else if(choix_pfc_joueur == ciseaux && choix_IA == pierre)
            {
                cout << "C'est "<< IA << " qui gagne !" << endl;
                point_IA += 1;
                cout << joueur << " : " << point_joueur << endl
                     << IA << " : " << point_IA << endl;
            }
            else if(choix_pfc_joueur == ciseaux && choix_IA == feuille)
            {
                cout << "C'est "<< joueur << " qui gagne !" << endl;
                point_joueur += 1;
                cout << joueur << " : " << point_joueur << endl
                     << IA << " : " << point_IA << endl;
            }

        }

         if(point_joueur > point_IA)
        {
            cout << joueur << ", tu as gagné avec " << point_joueur << " points !" << endl << endl;
            cout
                    << "                        ----------------Scores----------------" << endl
                    << "                        |Nom|" << joueur << "|Points|" << point_joueur << "|" << endl
                    << "                        |Nom|" << IA << "|Points|" << point_IA << "|" << endl;
        }
        else if(point_IA > point_joueur)
        {
            cout << IA << ", tu as gagné avec " << point_IA << " points !" << endl << endl;
            cout
                    << "                        ----------------Scores----------------" << endl
                    << "                        |Nom|" << IA << "|Points|" << point_IA << "|" << endl
                    << "                        |Nom|" << joueur << "|Points|" << point_joueur << "|" << endl;
        }
        reponse = "";
        point_joueur = 0;
        point_IA = 0;
        compteur = 0;

        cout << "Voulez-vous rejouer ? (O/N)" << endl;
        cin >> reponse;

        while(reponse != "O" && reponse != "N")
        {
            cout << "Entrez la bonne lettre" << endl;
            cin >> reponse;
        }
        if(reponse == "O")
        {
            cout << "Recommençons !" << endl;
        }
    }
}
void jeu_local()
{
    cout << "Vous jouez en local !" << endl;
    cout
            << "|----------------------------------------------------------------------------------------|"<< endl
            << "| Voici le jeu du pierre-feuille-ciseaux!                                                |"<< endl
            << "| Les règles sont simples :                                                              |"<< endl
            << "| Le joueur choisi son objet entre la pierre, la feuille et les ciseaux.                 |"<< endl
            << "| La pierre bat les ciseaux, les ciseaux battent la feuille et la feuille bat la pierre. |"<< endl
            << "| Celui qui remporte le tour gagne un point.                                             |"<< endl
            << "| Le premier à trois points remporte la partie.                                          |"<< endl
            << "|----------------------------------------------------------------------------------------|"<< endl;

    string joueur1;
    string joueur2;
    string pierre = "P";
    string feuille = "F";
    string ciseaux = "C";
    string choix_pfc_joueur1;
    string choix_pfc_joueur2;
    string selection = " appuye sur 'P' pour jouer la pierre, sur 'F' pour jouer la feuille et sur 'C' pour jouer les ciseaux.";
    int point_joueur1 = 0;
    int point_joueur2 = 0;
    int compteur = 0;
    string reponse = "O";


    cout << "Joueur1 rentrez votre pseudo : ";
    cin >> joueur1;
    cout << "Joueur2 rentrez votre pseudo : ";
    cin >> joueur2;
    cout << endl << "C'est parti pour le 1er tour !";

    while(reponse == "O")
    {
        while(point_joueur1 < 3 && point_joueur2 < 3)
        {
            compteur += 1;
            cout << endl << "Tour " << compteur << endl << endl;
            cout << joueur1 << selection << endl;
            cin >> choix_pfc_joueur1;
            while(choix_pfc_joueur1 != pierre && choix_pfc_joueur1 != feuille && choix_pfc_joueur1 != ciseaux)
            {
                cout << joueur1 << " entrez une lettre valide." << endl;
                cin >> choix_pfc_joueur1;
            }
            if(choix_pfc_joueur1 == pierre)
            {
                cout << "Tu as joué la pierre !" << endl;
            }
            else if(choix_pfc_joueur1 == feuille)
            {
                cout << "Tu as joué la feuille !" << endl;
            }
            else if(choix_pfc_joueur1 == ciseaux)
            {
                cout << "Tu as joué les ciseaux !" << endl;
            }
            Sleep(3000);
            system("cls");

            cout << joueur2 << selection << endl;
            cin >> choix_pfc_joueur2;
            while(choix_pfc_joueur2 != pierre && choix_pfc_joueur2 != feuille && choix_pfc_joueur2 != ciseaux)
            {
                cout << joueur2 << " entrez une lettre valide." << endl;
                cin >> choix_pfc_joueur2;
            }
            if(choix_pfc_joueur2 == pierre)
            {
                cout << "Tu as joué la pierre !" << endl;
            }
            else if(choix_pfc_joueur2 == feuille)
            {
                cout << "Tu as joué la feuille !" << endl;
            }
            else if(choix_pfc_joueur2 == ciseaux)
            {
                cout << "Tu as joué les ciseaux !" << endl;
            }
            Sleep(3000);
            system("cls");

            //Tester les conditions de victoire du joueur 1
            if(choix_pfc_joueur1 == choix_pfc_joueur2)
            {
                cout << "Egalité" << endl;
                cout << "Aucun de vous deux n'a gagné de points." << endl
                     << joueur1 << " : " << point_joueur1 << endl
                     << joueur2 << " : " << point_joueur2 << endl;

            }

            else if(choix_pfc_joueur1 == pierre && choix_pfc_joueur2 == feuille)
            {
                cout << "C'est "<< joueur2 << " qui gagne !" << endl;
                point_joueur2 += 1;
                cout << joueur1 << " : " << point_joueur1 << endl
                     << joueur2 << " : " << point_joueur2 << endl;
            }
            else if(choix_pfc_joueur1 == pierre && choix_pfc_joueur2 == ciseaux)
            {
                cout << "C'est "<< joueur1 << " qui gagne !" << endl;
                point_joueur1 += 1;
                cout << joueur1 << " : " << point_joueur1 << endl
                     << joueur2 << " : " << point_joueur2 << endl;
            }
            else if(choix_pfc_joueur1 == feuille && choix_pfc_joueur2 == pierre)
            {
                cout << "C'est "<< joueur1 << " qui gagne !" << endl;
                point_joueur1 += 1;
                cout << joueur1 << " : " << point_joueur1 << endl
                     << joueur2 << " : " << point_joueur2 << endl;
            }
            else if(choix_pfc_joueur1 == feuille && choix_pfc_joueur2 == ciseaux)
            {
                cout << "C'est "<< joueur2 << " qui gagne !" << endl;
                point_joueur2 += 1;
                cout << joueur1 << " : " << point_joueur1 << endl
                     << joueur2 << " : " << point_joueur2 << endl;
            }
            else if(choix_pfc_joueur1 == ciseaux && choix_pfc_joueur2 == pierre)
            {
                cout << "C'est "<< joueur2 << " qui gagne !" << endl;
                point_joueur2 += 1;
                cout << joueur1 << " : " << point_joueur1 << endl
                     << joueur2 << " : " << point_joueur2 << endl;
            }
            else if(choix_pfc_joueur1 == ciseaux && choix_pfc_joueur2 == feuille)
            {
                cout << "C'est "<< joueur1 << " qui gagne !" << endl;
                point_joueur1 += 1;
                cout << joueur1 << " : " << point_joueur1 << endl
                     << joueur2 << " : " << point_joueur2 << endl;
            }
        }

        if(point_joueur1 > point_joueur2)
        {
            cout << joueur1 << ", tu as gagné avec " << point_joueur1 << " points !" << endl << endl;
            cout
                    << "                        ----------------Scores----------------" << endl
                    << "                        |Nom|" << joueur1 << "|Points|" << point_joueur1 << "|" << endl
                    << "                        |Nom|" << joueur2 << "|Points|" << point_joueur2 << "|" << endl;
        }
        else if(point_joueur2 > point_joueur1)
        {
            cout << joueur2 << ", tu as gagné avec " << point_joueur2 << " points !" << endl << endl;
            cout
                    << "                        ----------------Scores----------------" << endl
                    << "                        |Nom|" << joueur2 << "|Points|" << point_joueur2 << "|" << endl
                    << "                        |Nom|" << joueur1 << "|Points|" << point_joueur1 << "|" << endl;
        }
        reponse = "";
        point_joueur1 = 0;
        point_joueur2 = 0;
        compteur = 0;

        cout << "Voulez-vous rejouer ? (O/N)" << endl;
        cin >> reponse;

        while(reponse != "O" && reponse != "N")
        {
            cout << "Entrez la bonne lettre" << endl;
            cin >> reponse;
        }
        if(reponse == "O")
        {
            cout << "Recommençons !" << endl;
        }
    }
}

void chifoumi()
{
    cout
    << "|----------------------------------------------------------------------|" << endl
    << "|                  Bienvenu dans le jeu du chifoumi !                  |" << endl
    << "|                                                                      |" << endl
    << "|         <Jouer contre l'IA>  <Jouer en local (deux joueurs)>         |" << endl
    << "|Appuyez sur '1' pour jouer contre l'IA ou sur '2' pour jouer en local.|" << endl
    << "|----------------------------------------------------------------------|" << endl;

    string choix;

    cin >> choix;

    while(choix != "1" && choix != "2"){
        cout << "Réessayez !" << endl;
        cin >> choix;
    }

    if(choix == "1")
    {
        jeu_contre_IA();
    }

    else
    {
       jeu_local();
    }
}

int main()
{
    SetConsoleOutputCP(65001);

    string reponse_u = "";

    reponse_u = entree_jeu();

    while(reponse_u != "Q")
        {
        if(reponse_u == "C"){
            cout << "Vous êtes dans le chifoumi !" << endl << "Voulez-vous continuer ? 'O'/'N'" << endl;
            cin >> reponse_u;
            if(reponse_u == "O"){
                cout << "On continue alors !" << endl;
                chifoumi();
            }
            else if(reponse_u == "N"){
                reponse_u = entree_jeu();
            }
            else{
                reponse_u = "N";
            }
        }
        else if(reponse_u == "S"){
            cout << "Vous êtes dans le snake !" << endl << "Voulez-vous continuer ? 'O'/'N'" << endl;
            cin >> reponse_u;
            if(reponse_u == "O"){
                cout << "On continue alors !" << endl;
                break;
            }
            else if(reponse_u == "N"){
                reponse_u = entree_jeu();
            }
            else if(reponse_u == "Q"){
                break;
            }
            else
            {
                reponse_u = retour();
            }
        }
        else
        {
            cout << "Réessayez." << endl;
            cin >> reponse_u;
        }
    }
}