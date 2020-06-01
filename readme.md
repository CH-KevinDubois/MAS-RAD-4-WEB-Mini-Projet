# Rapport de travail
## RDFs : description du modèle
### Intro
Le modèle tente de décrire simplement les interactions, les actions et les acteurs présents dans un restaurant. 
Le modèle est volontairement simplifié, mais pas trop afin de respecter les contraintes posées par les données mini-projet. Pour décire en deux mots le modèle : l'acteur principal est le restaurant qui possède des employés et qui est visité par des clients. Les employés servent les clients, émettent les factures, cuisinent les plats, ... . Alors que les clients choisissent des plats proposés dans la carte et peuvent donner des notes en fin de repas.  
image du schéma. 
### Classes
* Restaurant : Un restaurant avec un nom, un numéro de téléphone et une numéro de patente. Il tient un inventaire et a des employées.
* dbo:Person : Une personne avec un nom. Non instanciée, utilisée pour l'héritage.
* Employee : Un employé avec un salaire. Il travaille pour un restaurant et fait des réservations. Non instancié.
* Cook : Un cusinier. Il commande les commandes les produits et prépare les plats.
* Waiter : Un serveur. Il s'occupe des tables et émet les factures.
* Manager : Un manager. Il supervise les employées (donc possiblement d'autres managers), édite les factures et annule les réservations.
* Custormer : Un client avec un numéro de téléphone. Il fait une réservation et, à la fin du repas sur une base volontaire, note la préstation.
* Rank : Un classement avec une note. Il note un restaurant et une réservation sur la nourriture, le service et l'ambience. 
* Reservation : Une réservation avec une date de saisie et une date et heure cible. Elle est liée a une table.
* Table : Une table avec un numéro unique et le nombre de personnes pouvant y être assis. 
* Bill_of_sale : Une facture avec un pourboire. Elle est le total de l'addition des plats et des menus.
* Menu : Un menu avec un prix et un nom. Il contient des plats.
* Dish : Un plat avec un prix et un nom. Il est constitué de produits.
### Object properties
* offers :
    * RDFS:domain => Restaurant
    * RDFS:Range => Dish
* offers :
    * RDFS:domain => Restaurant
    * RDFS:Range => Menu
* works_for :
    * RDFS:domain => Employee
    * RDFS:Range => Restaurant
* has_taken :
    * RDFS:domain => Employee
    * RDFS:Range => Reservation
* supervises :
    * RDFS:domain => Manager
    * RDFS:Range => Employee
* has_edited :
    * RDFS:domain => Manager
    * RDFS:Range => Bill_of_sale
* has_edited :
    * RDFS:domain => Manager
    * RDFS:Range => Reservation
* looks_after :
    * RDFS:domain => Waiter
    * RDFS:Range => Table
* has_emitted :
    * RDFS:domain => Waiter
    * RDFS:Range => Bill_of_sale
* perpares :
    * RDFS:domain => Cook
    * RDFS:Range => Dish
* has_made :
    * RDFS:domain => Customer
    * RDFS:Range => Reservation
* has_made :
    * RDFS:domain => Customer
    * RDFS:Range => Rank
* ranks :
    * RDFS:domain => Rank
    * RDFS:Range => Restaurant
* ranks :
    * RDFS:domain => Rank
    * RDFS:Range => Reservation
* is_linked :
    * RDFS:domain => Table
    * RDFS:Range => Reservation
* is_linked :
    * RDFS:domain => Bill_of_sale
    * RDFS:Range => Reservation
* includes :
    * RDFS:domain => Bill_of_sale
    * RDFS:Range => Menu
* includes :
    * RDFS:domain => Bill_of_sale
    * RDFS:Range => Dish
* contains :
    * RDFS:domain => Menu
    * RDFS:Range => Dish
### Inférences
Une première idée est d'inférer la somme totale de la facture connaissant le prix ainsi que la quantité de menus et des plats commandés. 
Une seconde idée, comme nous avons les liens entre tous les acteurs d'un restaurant, est de mettre en relation le classement donné par client, l'équipe en charge de la table, ainsi que le pourboire laissé avec la facture. Selon une certaine pondération du classement et du pourboire, il est possible d'évaluer la performance de l'ensemble des employés, et par inférence de leur ajouter une propriété qui est la moyenne des notes reçues dans le passé.
## RDF : instanciation du modèle
Les graphs
## Requêtes SPARQL
1.  Requête : execQueryGetEmployees  
    Description : trouver tous les employés.  
    Utilité : trouver tous les employés.  
    Constuction : Je recherche toutes les subclasses de Employee et ensuite toutes les instances des ces subclasses (fait comme ceci car pas de reasoner).  
    Graph  
    ```java
    PREFIX db: <http://dbpedia.org/resource/>  
	PREFIX onto: <http://dbpedia.org/ontology/>   
	PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
	PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> 
	PREFIX ns: <http://www.semanticweb.org/Kevin/Mini-projet/Ressources/>
	select distinct ?employee where { 
		?x rdfs:subClassOf ns:Employee . 
		?employee rdf:type ?x . 	
	};
    ```
    Resulats attendus : 
    ```java 
    http://www.semanticweb.org/Kevin/Mini-projet/Ressources/jules
    http://www.semanticweb.org/Kevin/Mini-projet/Ressources/nicolas
    http://www.semanticweb.org/Kevin/Mini-projet/Ressources/philippe
    ```

2.  Requête : execQueryGetHigherDishPrice  
    Description : Optenir les 2 prix les plus élevés des plats.  
    Utilité :  Optenir les deux prix les plus élevés.  
    Constuction : Je recherche les plats, les prix associés, j'ordonne de façon DESC et je limite à deux résultats.  
    Graph  
    ```java
    PREFIX db: <http://dbpedia.org/resource/>  
	PREFIX onto: <http://dbpedia.org/ontology/>   
	PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
	PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> 
	PREFIX ns: <http://www.semanticweb.org/Kevin/Mini-projet/Ressources/>
    "select distinct ?price where { 
		?dish rdf:type ns:Dish . 
		?dish ns:price ?price . 	
	} 
	ORDER BY DESC(?price) 
	LIMIT 2	
	};
    ```
    Resulats attendus : 
    ```java 
    "22.0"^^<http://www.w3.org/2001/XMLSchema#decimal>
    "18.0"^^<http://www.w3.org/2001/XMLSchema#decimal>
    ```

3.  Requête : execQueryGetReservationOf  
    Description : Optenir les réservations d'un client.  
    Utilité :  Optenir les réservations d'un client.  
    Constuction : Je recherche tous les clients, toutes les réservations, quelles réservations ont faits les clients, le nom des clients je filtre sur le nom rechrerché.  
    Graph  
    ```java
    PREFIX db: <http://dbpedia.org/resource/>  
	PREFIX onto: <http://dbpedia.org/ontology/>   
	PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
	PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> 
	PREFIX ns: <http://www.semanticweb.org/Kevin/Mini-projet/Ressources/>
	select distinct ?reservation where {
		?customer rdf:type ns:Customer . 
		?reservation rdf:type ns:Reservation . 
	    ?customer ns:has_made ?reservation .
		?customer ns:name ?name . 
	FILTER(?name = "Marc")
	};
    ```
    Resulats attendus : 
    ```java 
    http://www.semanticweb.org/Kevin/Mini-projet/Ressources/reservation2
    ```

    4.  Requête : execQueryGetRestaurantMenuAndDishPrice  
    Description : Optenir les prix des plats et menus d'un restaurant.  
    Utilité :  Optenir les prix des plats et menus d'un restaurant.  
    Constuction : Je recherche tous les restaurants, toutes les plats et menus offerts, le nom des restaurants, le noms et le prix des plats et menus, et je filtre sur le nom du restaurant recherché. C'est peut être pas optimal mais ça marche.   
    Graph  
    ```java
    PREFIX db: <http://dbpedia.org/resource/>  
	PREFIX onto: <http://dbpedia.org/ontology/>   
	PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
	PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> 
	PREFIX ns: <http://www.semanticweb.org/Kevin/Mini-projet/Ressources/>
	select distinct ?dishAndMenuName ?price where { 
	    ?restaurant rdf:type ns:Restaurant . 
	    ?restaurant ns:offers ?dishAndMenu . 
		?restaurant ns:name ?name . 
		?dishAndMenu ns:name ?dishAndMenuName . 
		?dishAndMenu ns:price ?price . 
	FILTER(?name = "Le Cygne")
    };
    ```
    Resulats attendus : 
    ```java 
    "Dish1" "22.0"^^<http://www.w3.org/2001/XMLSchema#decimal>
    "Dish2" "18.0"^^<http://www.w3.org/2001/XMLSchema#decimal>
    "Dish3" "13.0"^^<http://www.w3.org/2001/XMLSchema#decimal>
    "Menu1" "35.0"^^<http://www.w3.org/2001/XMLSchema#decimal>
    "Menu2" "20.0"^^<http://www.w3.org/2001/XMLSchema#decimal>
    ```

    5.  Requête : execQueryGetEditedAndNonEditedBills  
    Description : Optenir les factures et si éditées le manager associé.  
    Utilité :  Optenir les factures et si éditées le manager associé.  
    Constuction : Je recherche toutes les factures et je fais un optional sur le manager.   
    Graph  
    ```java
    PREFIX db: <http://dbpedia.org/resource/>  
	PREFIX onto: <http://dbpedia.org/ontology/>   
	PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
	PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> 
	PREFIX ns: <http://www.semanticweb.org/Kevin/Mini-projet/Ressources/>
    select distinct ?bill ?manager where {
        ?bill rdf:type ns:Bill_of_sale . 						
	OPTIONAL {
	    ?manager ns:has_edited ?bill .
	}
    };
    ```
    Resulats attendus : 
    ```java 
    http://www.semanticweb.org/Kevin/Mini-projet/Ressources/bill1 null
    http://www.semanticweb.org/Kevin/Mini-projet/Ressources/bill2 http://www.semanticweb.org/Kevin/Mini-projet/Ressources/philippe
    ```

    6.  Requête : execQueryGetBillsWithMenuOrDishes  
    Description : Optenir les factures qui contiennent des menus ou des plats.  
    Utilité :  Optenir les factures qui contiennent des menus ou des plats.  
    Constuction : Je recherche tous les plats et les factures associées UNION je recherche tous les menus et les factures associées.  
    ```java
    PREFIX db: <http://dbpedia.org/resource/>  
	PREFIX onto: <http://dbpedia.org/ontology/>   
	PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
	PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> 
	PREFIX ns: <http://www.semanticweb.org/Kevin/Mini-projet/Ressources/>
	select  distinct ?bill where { 
		{   
            ?dish rdf:type ns:Dish .
		    ?bill ns:includes ?dish 
        }							
		UNION 
		{ 
            ?menu rdf:type ns:Menu .
		    ?bill ns:includes ?menu 
        } 
    };
    ```
    Resulats attendus : 
    ```java 
    http://www.semanticweb.org/Kevin/Mini-projet/Ressources/bill1
    http://www.semanticweb.org/Kevin/Mini-projet/Ressources/bill2
    ```