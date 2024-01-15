/*
#include <iostream>
#include <windows.h>

using namespace std;

/*string retour(string reponse, string jeu1, string jeu2){
        while(reponse!= jeu1 && reponse != jeu2){
        cout << "Choisissez une des deux lettres valides s'il vous plait !" << endl;
        cin >> reponse;
        }
        return reponse;
    }
string entree_jeu(string reponse = ""){
    cout << "Bienvenue dans FunGames !" << endl
    << "Nous vous proposons de selectionner un de ces deux jeux en tapant l'initiale du jeu : "
    << endl << "- Chifoumi (C)" << endl << "- Snake (S)" << endl;

    return reponse;
    }

string jeu_contre_IA(){
    //code
}
string jeu_local(){
    //code
}
int main()
{
    SetConsoleOutputCP(1252);

    string chifoumi = "C";
    string snake = "S";
    string accepter = "O";
    string refuser = "N";
    string reponse_u = "";

    entree_jeu();

    cin >> reponse_u;

    reponse_u = retour(reponse_u, chifoumi, snake);

    cout << reponse_u;

    if(reponse_u == chifoumi){
        cout << "Vous etes dans le chifoumi !" << endl << "Voulez-vous continuer ? 'O'/'N'" << endl;
        cin >> reponse_u;
        if(reponse_u == accepter){
            cout << "On continue alors !" << endl;
        }
        else if(reponse_u == refuser){
            entree_jeu();
        }
        else{
            retour(reponse_u, accepter, refuser);
        }
    }
    else if(reponse_u == snake){
        cout << "Vous etes dans le snake !" << endl << "Voulez-vous continuer ? 'O'/'N'" << endl;
        cin >> reponse_u;
        if(reponse_u == accepter){
            cout << "On continue alors !" << endl;
        }
        else if(reponse_u == refuser){
            entree_jeu();
        }
        else{
            retour(reponse_u, accepter, refuser);
        }
    }

    cout
    << "|----------------------------------------------------------------------|" << endl
    << "|                   Bienvenu dans le jeu du cifoumi !                  |" << endl << endl
    << "|         <Jouer contre l'IA>  <Jouer en local (deux joueurs)>         |" << endl
    << "|Appuyez sur '1' pour jouer contre l'IA ou sur '2' pour jouer en local.|" << endl
    << "|----------------------------------------------------------------------|" << endl;

    string choix;

    cin >> choix;

    while(choix != "1" && choix != "2"){
        cout << "Réessayez !" << endl;
        cin >> choix;
    }

    if(choix == "1"){
        cout << "Vous jouez contre l'IA !" << endl;
        //jeu_contre_IA();
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
        string pierre = "P";
        string feuille = "F";
        string ciseaux = "C";
        string choix_pfc_joueur;
        int point_joueur = 0;
        int point_IA = 0;
        int nb_tour = 0;

        cout << "Rentre ton pseudo : ";
        cin >> joueur;
        cout << "La partie peut commencer !" << endl;

        while(point_joueur < 3 || point_IA < 3)
        {
            nb_tour += 1;
            cout << endl << "Tour " << nb_tour << endl;
            cout << joueur << " appuye sur 'P' pour jouer la pierre, sur 'F' pour jouer la feuille et sur 'C' pour jouer les ciseaux." << endl;
            cin >> choix_pfc_joueur;

            while(choix_pfc_joueur != pierre && choix_pfc_joueur != feuille && choix_pfc_joueur != ciseaux)
            {
                cout << joueur << " appuye sur 'P' pour jouer la pierre, sur 'F' pour jouer la feuille et sur 'C' pour jouer les ciseaux." << endl;
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
        }
    }
    else if(choix == "2"){
        cout << "Vous jouez en local !" << endl;
        //jeu_local();
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


        cout << "Joueur1 rentrez votre pseudo : ";
        cin >> joueur1;
        cout << "Joueur2 rentrez votre pseudo : ";
        cin >> joueur2;
        cout << endl << "C'est parti pour le 1er tour !";


        for(int i = 1; i <=3 ; i++)
        {
            cout << endl << "Tour " << i << endl << endl;
            cout << joueur1 << selection << endl;
            cin >> choix_pfc_joueur1;
            while(choix_pfc_joueur1 != pierre && choix_pfc_joueur1 != feuille && choix_pfc_joueur1 != ciseaux)
            {
                cout << joueur1 << selection << endl;
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

            cout << joueur2 << selection << endl;
            cin >> choix_pfc_joueur2;
            while(choix_pfc_joueur2 != pierre && choix_pfc_joueur2 != feuille && choix_pfc_joueur2 != ciseaux)
            {
                cout << joueur2 << selection << endl;
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
            cout << joueur1 << ", tu as gagné avec " << point_joueur1 << " !" << endl << endl;
            cout
                    << "                        ----------------Scores----------------" << endl
                    << "                        |Nom|" << joueur1 << "|Points|" << point_joueur1 << "|" << endl
                    << "                        |Nom|" << joueur2 << "|Points|" << point_joueur2 << "|" << endl;
        }
        else if(point_joueur2 > point_joueur1)
        {
            cout << joueur2 << ", tu as gagné avec " << point_joueur2 << " !" << endl << endl;
            cout
                    << "                        ----------------Scores----------------" << endl
                    << "                        |Nom|" << joueur2 << "|Points|" << point_joueur2 << "|" << endl
                    << "                        |Nom|" << joueur1 << "|Points|" << point_joueur1 << "|" << endl;
        }
    }
}
*/