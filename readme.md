# Rapport de travail
## RDFs : description du modèle
### Intro
Le modèle tente de décrire simplement les interactions et les actions entre les acteurs présents dans un restaurant. 
Le modèle est volontairemenet simple afin de respecter les contraintes du mini-projet. Le restaurant possède des employés qui cusisinent et servent le client, le client choisi un plat proposé dans la carte du restaurant et peut le noter en fin de repas. 
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
* works_for :
    * RDFS:domain => Employee
    * RDFS:Range => Restaurant
* takes :
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
* has_emmited :
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
    * RDFS:domain => Reservation
    * RDFS:Range => Table
* is_linked :
    * RDFS:domain => Bill_of_sale
    * RDFS:Range => Table
* includes :
    * RDFS:domain => Bill_of_sale
    * RDFS:Range => Menu
* includes :
    * RDFS:domain => Bill_of_sale
    * RDFS:Range => Dish
* contains :
    * RDFS:domain => Menu
    * RDFS:Range => Dish
### Inférence
Comme nous avons les liens entre tous les acteurs d'un restaurant, une inférence possible est de mettre en relation le classement donné par client, l'équipe en charge de la table, ainsi que le pourboire laissé avec la facture. Par inférence et selon une certaine pondéartion du classement et du pourboire, il serait possible d'évaluer la performance de l'ensemble des employés. Et par inférence ajouter à l'employé une propriété qui est la moyenne des notes reçues dans le passé.
## RDF : instanciation du modèle
