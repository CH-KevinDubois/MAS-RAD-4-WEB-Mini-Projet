# Rapport de travail
## RDFs : description du modèle
### Intro
Le modèle pour l'exercice tente de décrire simplement les interactions et les actions entre les acteurs d'un réstaurant . Le modèle est volontairemenet simpliste et lacunaire afin de ne pas exagérement complexifier l'exercice. 
image du schéma. 
### Classes
* Restaurant : Un restaurant avec un nom, un numéro de téléphone et une numéro de patente. Il tient un inventaire et a des employées.
* dbo:Person : Une personne avec un nom et un numro de téléphone. Non instanciée, utilisée pour l'héritage.
* Employee : Un employé avec un salaire et une date d'embauche. Il travaille pour un restaurant et fait des réservations. Non instancié.
* Cook : Un cusinier. Il commande les commandes les produits et prépare les plats.
* Waiter : Un serveur. Il s'occupe des tables et émet les factures.
* Manager : Un manager. Il supervise les employées (donc possiblement d'autres managers), édite les factures et annule les réservations.
* Custormer : Un client. Il fait une réservation et, à la fin du repas, sur une base volontaire, note la préstation (nouriture, service, ambiance, etc. que je ne développerai pas dans ce modèle).
* Rank : Un classement avec une note. Il note un restaurant et une réservation. 
* Reservation : Une réservation avec une date de saisie et une date et heure cible. Elle est liée a une table.
* Table : Une table avec un numéro unique et le nombre de personnes pouvant y être assis. 
* Bill_of_sale : Une facture avec un pourboire. Elle est le total de l'addition des plats et des menus.
* Menu : Un menu avec un prix et un nom. Il contient des plats.
* Dish : Un plat avec un prix et un nom. Il est constitué de produits.
* Produit : Un produit avec un nom, un prix et la quantité. Il fait partie d'un inventaire.
* Inventory : Un inventraire avec un numéro de référence et la date du dernier inventaire.
### Object properties
* holds :
    * RDFS:domain => Restaurant
    * RDFS:Range => Inventory
* works_for :
    * RDFS:domain => Employee
    * RDFS:Range => Restaurant
* takes :
    * RDFS:domain => Employee
    * RDFS:Range => Reservation
* supervises :
    * RDFS:domain => Manager
    * RDFS:Range => Employee
* edits :
    * RDFS:domain => Manager
    * RDFS:Range => Bill_of_sale
* deletes :
    * RDFS:domain => Manager
    * RDFS:Range => Reservation
* looks_after :
    * RDFS:domain => Waiter
    * RDFS:Range => Table
* emits :
    * RDFS:domain => Waiter
    * RDFS:Range => Bill_of_sale
* commands :
    * RDFS:domain => Cook
    * RDFS:Range => Product
* perpares :
    * RDFS:domain => Cook
    * RDFS:Range => Dish
* makes :
    * RDFS:domain => Customer
    * RDFS:Range => Reservation
* makes :
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
* is_composed :
    * RDFS:domain => Bill_of_sale
    * RDFS:Range => Menu
* is_composed :
    * RDFS:domain => Bill_of_sale
    * RDFS:Range => Dish
* contains :
    * RDFS:domain => Menu
    * RDFS:Range => Dish
* constitutes :
    * RDFS:domain => Product
    * RDFS:Range => Dish
* is_part_of :
    * RDFS:domain => Product
    * RDFS:Range => Inventory
### Inférence
Comme nous avons des liens entre tous les acteurs d'un restaurant donné, une inférence possible est de mettre en relation la note du client avec l'équipe en charge de la table ainsi que le pourboire laissé avec la facture. Par inférence et selon la granulométrie du classement fait par le consommateur (comme notifié ci-dessus, pas développé dans le cadre de cet exercice), il serait possible d'évaluer la performance de l'ensemble des employés. On pourrait par inférence ajouter à l'employé une propriété est la moyenne des notes attribuées.
## RDF : instanciation du modèle
