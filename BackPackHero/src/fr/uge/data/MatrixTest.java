package fr.uge.test;

public class Matrixtest {
	//======================TRANSPOSER D'UNE MATRICE
	public static int[][] transposeMatrix(int[][] matrix) {
	    int rows = matrix.length;
	    int cols = matrix[0].length;

	    int[][] transposedMatrix = new int[cols][rows];

	    for (int i = 0; i < rows; i++) {
	        for (int j = 0; j < cols; j++) {
	            transposedMatrix[j][i] = matrix[i][j];
	        }
	    }

	    return transposedMatrix;
	}
	
	//======================VERIFIE SI DEUX MATRICES ONT LA MEME TAILLE
	public static boolean matricesHaveSameSize(int[][] matrix1, int[][] matrix2) {
	    int rows1 = matrix1.length;
	    int cols1 = matrix1[0].length;

	    int rows2 = matrix2.length;
	    int cols2 = matrix2[0].length;

	    return (rows1 == rows2) && (cols1 == cols2);
	}
	
	
	//======================VERIFIE LES ENDROITS OU L'OBJETS PEUT RENRTRER
	public static int[] isProximity(int[][] inventory, int[][] object) {
	    int rows = inventory.length;
	    int cols = inventory[0].length;
	    int objRows = object.length;
	    int objCols = object[0].length;

	    // Parcours de l'inventaire pour trouver l'endroit le plus proche
	    for (int i = 0; i < rows; i++) {
	        for (int j = 0; j < cols; j++) {
	            boolean fits = true;

	            // Vérification si l'objet peut être inséré à l'endroit actuel de l'inventaire
	            for (int k = 0; k < objRows; k++) {
	                for (int l = 0; l < objCols; l++) {
	                    int row = i + k;
	                    int col = j + l;

	                    // Vérification si l'endroit où l'objet doit être inséré est en dehors des limites de l'inventaire
	                    if (row >= rows || col >= cols) {
	                        fits = false;
	                        break;
	                    }

	                    // Vérification si l'endroit où l'objet doit être inséré est déjà occupé
	                    if (inventory[row][col] != 0) {
	                        fits = false;
	                        break;
	                    }
	                }

	                if (!fits) {
	                    break;
	                }
	            }

	            // Si l'objet peut être inséré à l'endroit actuel de l'inventaire, on retourne l'indice de cet endroit
	            if (fits) {
	                return new int[] {i, j};
	            }
	        }
	    }

	    // Si aucun endroit n'a été trouvé où l'objet peut être inséré, on retourne null
	    return null;
	}
	
	
	
	
	//======================RECHERCHE LA POSITION DE L'OBJET
	//cette fct est utilé dans les moveobjectup dow right left
	private static int[] findObjectPosition(int[][] inventory, int[][] object) {
	    int rows = inventory.length;
	    int cols = inventory[0].length;

	    // Parcours de l'inventaire pour chercher l'objet
	    for (int i = 0; i < rows - object.length + 1; i++) {
	        for (int j = 0; j < cols - object[0].length + 1; j++) {
	            boolean objectFound = true;

	            // Vérifie si l'objet se trouve à cette position
	            for (int k = 0; k < object.length; k++) {
	                for (int l = 0; l < object[0].length; l++) {
	                    if (object[k][l] != 0 && inventory[i+k][j+l] != object[k][l]) {
	                        objectFound = false;
	                        break;
	                    }
	                }
	                if (!objectFound) {
	                    break;
	                }
	            }

	            // Si l'objet est trouvé, renvoie la position
	            if (objectFound) {
	                return new int[] {i, j};
	            }
	        }
	    }

	    // Si l'objet n'est pas trouvé, renvoie null
	    return null;
	}
	
	
	
	
	//==============================Mets l'objets dans l'inventaire
	//cette fct est utilé dans les moveobjectup dow right left
	
	
	private static void moveObject(int[][] inventory, int[][] object, int newRow, int newCol) {
	    int oldRow = -1;
	    int oldCol = -1;

	    // Trouve la position actuelle de l'objet
	    int[] objectPos = findObjectPosition(inventory, object);
	    if (objectPos != null) {
	        oldRow = objectPos[0];
	        oldCol = objectPos[1];
	    } else {
	        // L'objet n'a pas été trouvé dans l'inventaire
	        return;
	    }

	    // Vérifie si la nouvelle position est valide
	    if (newRow < 0 || newRow + object.length > inventory.length
	            || newCol < 0 || newCol + object[0].length > inventory[0].length) {
	        // La nouvelle position n'est pas valide
	        return;
	    }

	    // Vérifie si la nouvelle position est libre
	    for (int i = 0; i < object.length; i++) {
	        for (int j = 0; j < object[0].length; j++) {
	            if (object[i][j] != 0 && inventory[newRow+i][newCol+j] != 0) {
	                // La nouvelle position n'est pas libre
	                return;
	            }
	        }
	    }

	    // Déplace l'objet vers la nouvelle position
	    for (int i = 0; i < object.length; i++) {
	        for (int j = 0; j < object[0].length; j++) {
	            inventory[newRow+i][newCol+j] = object[i][j];
	            inventory[oldRow+i][oldCol+j] = 0;
	        }
	    }
	}

	
	
	//====================LORSQUE L'OBJET EST DAN L'INVENTAIRE ON DOIT POUVOIR LE BOUGER À L'INTERIEUR DE L'INVENTAIRE
	
	
	public static void moveObjectUp(int[][] inventory, int[][] object) {
	    int[] pos = findObjectPosition(inventory, object);

	    if (pos[0] > 0 && canMoveObjectUp(inventory, object, pos)) {
	        moveObject(inventory, object, pos[0] - 1, pos[1]);
	    }
	}

	public static void moveObjectDown(int[][] inventory, int[][] object) {
	    int[] pos = findObjectPosition(inventory, object);
	    int rows = inventory.length;

	    if (pos[0] < rows - object.length && canMoveObjectDown(inventory, object, pos)) {
	        moveObject(inventory, object, pos[0] + 1, pos[1]);
	    }
	}

	public static void moveObjectLeft(int[][] inventory, int[][] object) {
	    int[] pos = findObjectPosition(inventory, object);

	    if (pos[1] > 0 && canMoveObjectLeft(inventory, object, pos)) {
	        moveObject(inventory, object, pos[0], pos[1] - 1);
	    }
	}

	public static void moveObjectRight(int[][] inventory, int[][] object) {
	    int[] pos = findObjectPosition(inventory, object);
	    int cols = inventory[0].length;

	    if (pos[1] < cols - object[0].length && canMoveObjectRight(inventory, object, pos)) {
	        moveObject(inventory, object, pos[0], pos[1] + 1);
	    }
	}
	
	
	
	
	//==========Les CANMOVE SERT à vérifier si l'objets est bougeable
	
	
	
	private static boolean canMoveObjectUp(int[][] inventory, int[][] object, int[] pos) {
	    int rows = inventory.length;

	    // Vérifie si l'objet peut être déplacé vers le haut
	    for (int i = 0; i < object.length; i++) {
	        for (int j = 0; j < object[0].length; j++) {
	            int row = pos[0] + i - 1;
	            int col = pos[1] + j;

	            // Vérifie si la case existe et est vide
	            if (row < 0 || inventory[row][col] != 0) {
	                return false;
	            }
	        }
	    }

	    return true;
	}

	private static boolean canMoveObjectDown(int[][] inventory, int[][] object, int[] pos) {
	    int rows = inventory.length;

	    // Vérifie si l'objet peut être déplacé vers le bas
	    for (int i = 0; i < object.length; i++) {
	        for (int j = 0; j < object[0].length; j++) {
	            int row = pos[0] + i + 1;
	            int col = pos[1] + j;

	            // Vérifie si la case existe et est vide
	            if (row >= rows || inventory[row][col] != 0) {
	                return false;
	            }
	        }
	    }

	    return true;
	}

	private static boolean canMoveObjectLeft(int[][] inventory, int[][] object, int[] pos) {
	    int cols = inventory[0].length;

	    // Vérifie si l'objet peut être déplacé vers la gauche
	    for (int i = 0; i < object.length; i++) {
	        for (int j = 0; j < object[0].length; j++) {
	            int row = pos[0] + i;
	            int col = pos[1] + j - 1;

	            // Vérifie si la case existe et est vide
	            if (col < 0 || inventory[row][col] != 0) {
	                return false;
	            }
	        }
	    }

	    return true;
	}

	private static boolean canMoveObjectRight(int[][] inventory, int[][] object, int[] pos) {
	    int cols = inventory[0].length;

	    // Vérifie si l'objet peut être déplacé vers la droite
	    for (int i = 0; i < object.length; i++) {
	        for (int j = 0; j < object[0].length; j++) {
	            int row = pos[0] + i;
	            int col = pos[1] + j + 1;

	            // Vérifie si la case existe et est vide
	            if (col >= cols || inventory[row][col] != 0) {
	                return false;
	            }
	        }
	    }

	    return true;
	}
	




	
	
	
}
