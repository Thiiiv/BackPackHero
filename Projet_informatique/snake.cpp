/*
#include <iostream>
#define NOMINMAX
#include <windows.h>

using namespace std;

string entree_jeu(string reponse){
        SetConsoleOutputCP(1252);
    cout
    << "                                                                                                          " << endl
    << "                |----------------------------------------------------------------------------------------|" << endl
    << "                | Bienvenue dans FunGames !                                                              |" << endl
    << "                | Nous vous proposons de sélectionner un de ces deux jeux en tapant l'initiale du jeu :  |" << endl
    << "                | - Chifoumi (C)                                                                         |" << endl
    << "                | - Snake (S)                                                                            |" << endl
    << "                |----------------------------------------------------------------------------------------|" << endl
    << "                                                                                                          " << endl;

    return reponse;
    }

string retour(string reponse, string jeu1, string jeu2){
        SetConsoleOutputCP(1252);
        while(reponse!= jeu1 && reponse != jeu2){
        cout << "Choisissez une des deux lettres valides s'il vous plaît !" << endl;
        cin >> reponse;
        }
        return reponse;
    }

int main()
{
    SetConsoleOutputCP(1252);

    string chifoumi = "C";
    string snake = "S";
    string accepter = "O";
    string refuser = "N";
    string reponse_jeu = "";
    string reponse_conf = "";

    entree_jeu(reponse_jeu);

    cin >> reponse_jeu;

    reponse_jeu = retour(reponse_jeu, chifoumi, snake);

    while(reponse_jeu == chifoumi){
        //if(reponse_jeu == chifoumi){
            cout << endl << "Vous êtes dans le chifoumi !" << endl << "Voulez-vous continuer ? 'O'/'N'" << endl;
            cin >> reponse_conf;
            if(reponse_conf == accepter){
                cout << "On continue alors !" << endl;
            }
            else if(reponse_conf == refuser){
                entree_jeu(reponse_jeu);
                cin >> reponse_jeu;
                retour(reponse_jeu, chifoumi, snake);
            }
            else{
                retour(reponse_jeu, accepter, refuser);
            }
        //}
    }
    while(reponse_jeu == snake){
        //if(reponse_jeu == snake){
            cout << endl << "Vous êtes dans le snake !" << endl << "Voulez-vous continuer ? 'O'/'N'" << endl;
            cin >> reponse_conf;
            if(reponse_conf == accepter){
                cout << "On continue alors !" << endl;
            }
            else if(reponse_conf == refuser){
                    entree_jeu(reponse_jeu);
                    cin >> reponse_jeu;
                    retour(reponse_jeu, chifoumi, snake);
            }
            else{
                retour(reponse_jeu, accepter, refuser);
            }
        //}
    }
}
*/