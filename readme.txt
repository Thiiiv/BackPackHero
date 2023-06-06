SARBOUDINE Thamiz
JEYASEELAN Thivakar
===================================================================================================
	
	I. Ce qui a été implémenté:
	===========================
	- une map codée en dur
	- un sac
	- les combats contre les monstres
	- le healer
	
	II.L'organisation du programe
	=============================
	- les classes concernant les étages et salle sont dans le package : "fr.game.data"
	- les classes concernant les items sont dans le package : "fr.game.data.item"
	- les classes concernant les personnages sont dans le package : "fr.game.data.character"
	
	Vous pouvez voir la hiérarchisation des classes situées dans les packages cité ci-dessus dans le diagramme de classe présent dans le rendu du projet au même niveau que ce "readme.txt" sous le nom de "diagramclasse.png". 
	Il s'agit d'un ancien diagramme, cependant la hierachie des classes n'a, pour la majorité, pas changé, seul les méthodes contenues à l'intérieur ont changé.
	
	III.Choix techniques et algorithmiques
	======================================
	Pour ce projet nous avons utilisé la méthode MVC vue en IHM, cette méthode est caractérisée, dans notre projet, par les classes GameData, GameController et GameView dans le package "fr.game.data.game".
	
	IV.Les améliorations
	====================
	A) Progression
	==============
	- Réalisation des combats
	- Realisation du healer
	- Réalisation d'un sac utilisable (en combat)
	
	B) Prise en compte des remarques
	================================
	- Créations d'un javadoc
	- Amélioration du diagramme des classes avec les conseil du professeurs
	
	V.Les Problèmes rencontrés
	==========================
	- Le déplacement des items pour le sac a été abandonné, c'est pour cela qu'une grosse partie du code de gameController concernant le deplacement des items est en commentaire. Ce qui nous a fait perdre beaucoup de temps.
	- La solution pour la génération aléatoire d'étages n'a pu être discuté, dû au manque de temps et d'organisation.
