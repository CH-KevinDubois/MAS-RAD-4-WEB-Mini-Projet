import java.io.Console;
import java.io.File;

import org.eclipse.rdf4j.common.iteration.Iterations;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.LinkedHashModel;
import org.eclipse.rdf4j.model.vocabulary.FOAF;
import org.eclipse.rdf4j.model.vocabulary.OWL;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import org.eclipse.rdf4j.model.vocabulary.XMLSchema;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryResult;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.sail.memory.MemoryStore;
import org.mapdb.DB;

public class OntologyRestaurant {
	
	private static String namespace = "http://www.semanticweb.org/Kevin/Mini-projet/Ressources/";
	private static String dboNamespace = "http://dbpedia.org/ontology/";
	
	private static IRI Restaurant, offers, 
		Person, 
		Employee, has_taken, 
		Customer, has_made, 
		Rank, ranks, 
		Cook, prepares, 
		Waiter, has_emitted, looks_after, 
		Manager, has_edited, supervises, 
		Bill_of_sale, includes, 
		Table, is_linked, 
		Reservation, 
		Menu, contains, 
		Dish;
	
	private static IRI name, phone, food, staff, ambience, 
		salary, entry_date, target_date, 
		number, number_of_seats, tip, price;
	
	private static IRI le_Cygne, 
		jean, marc,
		rank1, rank2,
		jules,
		nicolas,
		philippe,
		bill1, bill2,
		table1, table2,
		reservation1, reservation2,
		menu1, menu2,
		dish1, dish2, dish3;
	
	static void buildOntology(Repository rep) {
				
		RepositoryConnection conn = rep.getConnection();
		ValueFactory f = rep.getValueFactory();
		
		// IRI Classes and object properties
		Restaurant = f.createIRI(namespace, "Restaurant");
		offers = f.createIRI(namespace, "offers");
		
		Person = f.createIRI(dboNamespace, "Person");
		
		Employee = f.createIRI(namespace, "Employee");
		has_taken = f.createIRI(namespace, "has_taken");
		
		Customer = f.createIRI(namespace, "Customer");
		has_made = f.createIRI(namespace, "has_made");
		
		Rank = f.createIRI(namespace, "Rank");
		ranks = f.createIRI(namespace, "ranks");
		
		Cook = f.createIRI(namespace, "Cook");
		prepares = f.createIRI(namespace, "prepares");
		
		Waiter = f.createIRI(namespace, "Waiter");
		has_emitted = f.createIRI(namespace, "has_emitted");
		looks_after = f.createIRI(namespace, "looks_after");
		
		Manager = f.createIRI(namespace, "Manager");
		has_edited = f.createIRI(namespace, "has_edited");
		supervises = f.createIRI(namespace, "has_edited");
		
		Bill_of_sale = f.createIRI(namespace, "Bill_of_sale");
		includes = f.createIRI(namespace, "includes");
		
		Table = f.createIRI(namespace, "Table");
		is_linked = f.createIRI(namespace, "is_linked");
		
		Reservation = f.createIRI(namespace, "Reservation");
		
		Menu = f.createIRI(namespace, "Menu");
		contains = f.createIRI(namespace, "contains");
		
		Dish = f.createIRI(namespace, "Dish");
		
		//IRI object variables		
		name = f.createIRI(namespace, "name");
		phone = f.createIRI(namespace, "phone");
		food = f.createIRI(namespace, "food");
		staff = f.createIRI(namespace, "staff");
		ambience = f.createIRI(namespace, "ambience");
		salary = f.createIRI(dboNamespace, "salary");
		entry_date = f.createIRI(namespace, "entry_date");
		target_date = f.createIRI(namespace, "target_date");
		number = f.createIRI(namespace, "number");
		number_of_seats = f.createIRI(namespace, "number_of_seats");
		tip = f.createIRI(namespace, "tip");
		price = f.createIRI(namespace, "price");
		
		//IRI instances	
		le_Cygne = f.createIRI(namespace, "le_Cygne"); 
		jean = f.createIRI(namespace, "jean");
		marc = f.createIRI(namespace, "marc");
		rank1 = f.createIRI(namespace, "rank1"); 
		rank2 = f.createIRI(namespace, "rank2");
		jules = f.createIRI(namespace, "jules");
		nicolas = f.createIRI(namespace, "nicolas");
		philippe = f.createIRI(namespace, "philippe");
		bill1 = f.createIRI(namespace, "bill1");
		bill2 = f.createIRI(namespace, "bill2");
		table1 = f.createIRI(namespace, "table1"); 
		table2 = f.createIRI(namespace, "table2");
		reservation1 = f.createIRI(namespace, "reservation1");
		reservation2 = f.createIRI(namespace, "reservation2");
		menu1 = f.createIRI(namespace, "menu1");
		menu2 = f.createIRI(namespace, "menu2");
		dish1 = f.createIRI(namespace, "dish1");
		dish2 = f.createIRI(namespace, "dish2");
		dish3 = f.createIRI(namespace, "dish3");
		
		
		try {
			conn.add(Restaurant, RDF.TYPE, RDFS.CLASS);
			conn.add(offers, RDF.TYPE, RDF.PREDICATE);
			
			conn.add(Customer, RDF.TYPE, RDFS.CLASS);
			conn.add(Customer, RDFS.SUBCLASSOF, Person);
			conn.add(has_made, RDF.TYPE, RDF.PREDICATE);
			
			conn.add(Employee, RDF.TYPE, RDFS.CLASS);
			conn.add(Employee, RDFS.SUBCLASSOF, Person);
			conn.add(has_taken, RDF.TYPE, RDF.PREDICATE);
			
			conn.add(Cook, RDF.TYPE, RDFS.CLASS);
			conn.add(Cook, RDFS.SUBCLASSOF, Employee);
			conn.add(prepares, RDF.TYPE, RDF.PREDICATE);
			
			conn.add(Waiter, RDF.TYPE, RDFS.CLASS);
			conn.add(Waiter, RDFS.SUBCLASSOF, Employee);
			conn.add(has_emitted, RDF.TYPE, RDF.PREDICATE);
			conn.add(has_emitted, RDFS.SUBPROPERTYOF, has_edited);
			conn.add(looks_after, RDF.TYPE, RDF.PREDICATE);
			
			conn.add(Manager, RDF.TYPE, RDFS.CLASS);
			conn.add(Manager, RDFS.SUBCLASSOF, Employee);
			conn.add(has_edited, RDF.TYPE, RDF.PREDICATE);
			conn.add(has_edited, RDFS.SUBPROPERTYOF, has_taken);
			conn.add(supervises, RDF.TYPE, RDF.PREDICATE);
			
			conn.add(Rank, RDF.TYPE, RDFS.CLASS);
			conn.add(ranks, RDF.TYPE, RDF.PREDICATE);

			conn.add(Bill_of_sale, RDF.TYPE, RDFS.CLASS);
			conn.add(includes, RDF.TYPE, RDF.PREDICATE);
			
			conn.add(Table, RDF.TYPE, RDFS.CLASS);
			conn.add(is_linked, RDF.TYPE, RDF.PREDICATE);
			
			conn.add(Reservation, RDF.TYPE, RDFS.CLASS);
			
			conn.add(Menu, RDF.TYPE, RDFS.CLASS);
			conn.add(contains, RDF.TYPE, RDF.PREDICATE);
			
			conn.add(Dish, RDF.TYPE, RDFS.CLASS);			
			
		} finally {
			conn.close();
		}
	}
	
	static void createIndividualsCustomer(Repository rep, String name, String phone, IRI iri) {
		
		RepositoryConnection conn = rep.getConnection();
		ValueFactory f = rep.getValueFactory();
		try {
			conn.add(iri, RDF.TYPE, Customer);
			conn.add(iri, OntologyRestaurant.name, f.createLiteral(name, XMLSchema.STRING));
			conn.add(iri, OntologyRestaurant.phone, f.createLiteral(phone, XMLSchema.STRING));
		} finally {
			conn.close();
		}
	}
	
	static void createIndividualsCook(Repository rep, String name, Double salary, IRI iri) {
		
		RepositoryConnection conn = rep.getConnection();
		ValueFactory f = rep.getValueFactory();
		try {
			conn.add(iri, RDF.TYPE, OntologyRestaurant.Cook);
			conn.add(iri, OntologyRestaurant.name, f.createLiteral(name, XMLSchema.STRING));
			conn.add(iri, OntologyRestaurant.salary, f.createLiteral(salary.toString(), XMLSchema.DECIMAL));
		} finally {
			conn.close();
		}
	}
	
	static void createIndividualsWaiter(Repository rep, String name, Double salary, IRI iri) {
		
		RepositoryConnection conn = rep.getConnection();
		ValueFactory f = rep.getValueFactory();
		try {
			conn.add(iri, RDF.TYPE, OntologyRestaurant.Waiter);
			conn.add(iri, OntologyRestaurant.name, f.createLiteral(name, XMLSchema.STRING));
			conn.add(iri, OntologyRestaurant.salary, f.createLiteral(salary.toString(), XMLSchema.DECIMAL));
		} finally {
			conn.close();
		}
	}
	
	static void createIndividualsManager(Repository rep, String name, Double salary, IRI iri) {

		RepositoryConnection conn = rep.getConnection();
		ValueFactory f = rep.getValueFactory();
		try {
			conn.add(iri, RDF.TYPE, OntologyRestaurant.Manager);
			conn.add(iri, OntologyRestaurant.name, f.createLiteral(name, XMLSchema.STRING));
			conn.add(iri, OntologyRestaurant.salary, f.createLiteral(salary.toString(), XMLSchema.DECIMAL));
		} finally {
			conn.close();
		}
	}
	
	static void createIndividualsRank(Repository rep, Byte food,  Byte staff, Byte ambiance, IRI iri) {
		
		RepositoryConnection conn = rep.getConnection();
		ValueFactory f = rep.getValueFactory();
		try {
			conn.add(iri, RDF.TYPE, OntologyRestaurant.Rank);
			conn.add(iri, OntologyRestaurant.food, f.createLiteral(food.toString(), XMLSchema.UNSIGNED_BYTE));
			conn.add(iri, OntologyRestaurant.staff, f.createLiteral(staff.toString(), XMLSchema.UNSIGNED_BYTE));
			conn.add(iri, OntologyRestaurant.ambience, f.createLiteral(ambience.toString(), XMLSchema.UNSIGNED_BYTE));
		} finally {
			conn.close();
		}
	}
	
	static void createIndividualsRestaurant(Repository rep, String name, String phone, IRI iri) {

		RepositoryConnection conn = rep.getConnection();
		ValueFactory f = rep.getValueFactory();
		try {
			conn.add(iri, RDF.TYPE, Restaurant);
			conn.add(iri, OntologyRestaurant.name, f.createLiteral(name, XMLSchema.STRING));
			conn.add(iri, OntologyRestaurant.phone, f.createLiteral(phone, XMLSchema.STRING));
		} finally {
			conn.close();
		}
	}
	
	static void createIndividualsResevation(Repository rep, String entry_date, String target_date, IRI iri) {
		
		RepositoryConnection conn = rep.getConnection();
		ValueFactory f = rep.getValueFactory();
		try {
			conn.add(iri, RDF.TYPE, OntologyRestaurant.Reservation);
			conn.add(iri, OntologyRestaurant.entry_date, f.createLiteral(entry_date, XMLSchema.DATETIME));
			conn.add(iri, OntologyRestaurant.target_date, f.createLiteral(target_date, XMLSchema.DATETIME));
		} finally {
			conn.close();
		}
	}
	
	static void createIndividualsBillOfSale(Repository rep, Double tip, IRI iri) {
		RepositoryConnection conn = rep.getConnection();
		ValueFactory f = rep.getValueFactory();
		try {
			conn.add(iri, RDF.TYPE, OntologyRestaurant.Bill_of_sale);
			conn.add(iri, OntologyRestaurant.tip, f.createLiteral(tip.toString(), XMLSchema.DECIMAL));
		} finally {
			conn.close();
		}
	}
	
	static void createIndividualsTable(Repository rep, Byte number,  Byte number_of_seats, IRI iri) {
		
		RepositoryConnection conn = rep.getConnection();
		ValueFactory f = rep.getValueFactory();
		try {
			conn.add(iri, RDF.TYPE, OntologyRestaurant.Table);
			conn.add(iri, OntologyRestaurant.number, f.createLiteral(number.toString(), XMLSchema.UNSIGNED_BYTE));
			conn.add(iri, OntologyRestaurant.number_of_seats, f.createLiteral(number_of_seats.toString(), XMLSchema.UNSIGNED_BYTE));
		} finally {
			conn.close();
		}
	}
	
	static void createIndividualsMenu(Repository rep, String name, Double price, IRI iri) {
		
		RepositoryConnection conn = rep.getConnection();
		ValueFactory f = rep.getValueFactory();
		try {
			conn.add(iri, RDF.TYPE, OntologyRestaurant.Menu);
			conn.add(iri, OntologyRestaurant.name, f.createLiteral(name, XMLSchema.STRING));
			conn.add(iri, OntologyRestaurant.price, f.createLiteral(price.toString(), XMLSchema.DECIMAL));
		} finally {
			conn.close();
		}
	}
	
	static void createIndividualsDish(Repository rep, String name, Double price, IRI iri) {
		
		RepositoryConnection conn = rep.getConnection();
		ValueFactory f = rep.getValueFactory();
		try {
			conn.add(iri, RDF.TYPE, OntologyRestaurant.Dish);
			conn.add(iri, OntologyRestaurant.name, f.createLiteral(name, XMLSchema.STRING));
			conn.add(iri, OntologyRestaurant.price, f.createLiteral(price.toString(), XMLSchema.DECIMAL));
		} finally {
			conn.close();
		}
	}
	
	static void createIndividuals(Repository rep) {

		createIndividualsRestaurant(rep, "Le Cygne", "+41 234 56 78", le_Cygne);
		createIndividualsCustomer(rep, "Jean", "+41 234 56 80", jean);
		createIndividualsCustomer(rep, "Marc", "+41 234 56 78", marc);
		createIndividualsRank(rep, (byte)8, (byte)5, (byte)9, rank1);
		createIndividualsRank(rep, (byte)4, (byte)10, (byte)4, rank2);
		createIndividualsCook(rep, "Jules", 5000., jules);
		createIndividualsWaiter(rep, "Nicolas", 4000., nicolas);
		createIndividualsManager(rep, "Philippe", 5000., philippe);
		createIndividualsBillOfSale(rep, 5., bill1);
		createIndividualsBillOfSale(rep, 75., bill2);
		createIndividualsTable(rep, (byte)1, (byte)6, table1);
		createIndividualsTable(rep, (byte)2, (byte)2, table2);
		createIndividualsResevation(rep, "2020-26-01T10:10Z", "2020-27-02T20:15Z", reservation1);
		createIndividualsResevation(rep, "2020-26-01T09:09Z", "2020-28-02T20:15Z", reservation2);
		createIndividualsDish(rep, "Dish1", 22., dish1);
		createIndividualsDish(rep, "Dish2", 18., dish2);
		createIndividualsDish(rep, "Dish3", 13., dish3);
		createIndividualsMenu(rep, "Menu1", 35., menu1);
		createIndividualsMenu(rep, "Menu2", 20., menu2);	
	}
	
	static void linkIndividuals(Repository rep) {
		
		// Object properties
		RepositoryConnection conn = rep.getConnection();
		ValueFactory f = rep.getValueFactory();
		try {
			conn.add(le_Cygne, OntologyRestaurant.offers, dish1);
			conn.add(le_Cygne, OntologyRestaurant.offers, dish2);
			conn.add(le_Cygne, OntologyRestaurant.offers, dish3);
			conn.add(le_Cygne, OntologyRestaurant.offers, menu1);
			conn.add(le_Cygne, OntologyRestaurant.offers, menu2);
			
			conn.add(jean, OntologyRestaurant.has_made, rank1);
			conn.add(jean, OntologyRestaurant.has_made, reservation1);
			conn.add(marc, OntologyRestaurant.has_made, rank2);
			conn.add(marc, OntologyRestaurant.has_made, reservation2);
			
			conn.add(rank1, OntologyRestaurant.ranks, reservation1);
			conn.add(rank1, OntologyRestaurant.ranks, le_Cygne);
			conn.add(rank2, OntologyRestaurant.ranks, reservation2);
			conn.add(rank2, OntologyRestaurant.ranks, le_Cygne);
			
			conn.add(jules, OntologyRestaurant.prepares, dish1);
			conn.add(jules, OntologyRestaurant.prepares, dish2);
			conn.add(jules, OntologyRestaurant.prepares, dish3);
			conn.add(jules, OntologyRestaurant.has_taken, reservation1);
			
			conn.add(nicolas, OntologyRestaurant.looks_after, table1);
			conn.add(nicolas, OntologyRestaurant.looks_after, table2);
			conn.add(nicolas, OntologyRestaurant.has_taken, reservation2);
			conn.add(nicolas, OntologyRestaurant.has_emitted, bill1);
			conn.add(nicolas, OntologyRestaurant.has_emitted, bill2);
			
			conn.add(philippe, OntologyRestaurant.has_edited, bill2);
			conn.add(philippe, OntologyRestaurant.has_edited, reservation2);
			conn.add(philippe, OntologyRestaurant.supervises, jules);
			conn.add(philippe, OntologyRestaurant.supervises, nicolas);
			
			conn.add(bill1, OntologyRestaurant.is_linked, reservation1);
			conn.add(bill1, OntologyRestaurant.includes, dish1);
			conn.add(bill1, OntologyRestaurant.includes, dish3);
			
			conn.add(bill2, OntologyRestaurant.is_linked, reservation2);
			conn.add(bill2, OntologyRestaurant.includes, menu2);
			conn.add(bill2, OntologyRestaurant.includes, dish2);
			
			conn.add(table1, OntologyRestaurant.is_linked, reservation1);
			conn.add(table2, OntologyRestaurant.is_linked, reservation2);
			
			conn.add(menu1, OntologyRestaurant.contains, dish1);
			conn.add(menu1, OntologyRestaurant.contains, dish2);
			conn.add(menu2, OntologyRestaurant.contains, dish2);
			conn.add(menu2, OntologyRestaurant.contains, dish3);
		} finally {
			conn.close();
		}
	}
	
	static void displayRepositoryTurtleFormat(Repository rep) {
	
		RepositoryConnection conn = rep.getConnection();
		
		RepositoryResult<Statement> statements = conn.getStatements (null, null, null, true);
		Model model = Iterations.addAll(statements, new LinkedHashModel());	
		
		model.setNamespace("rdf", RDF.NAMESPACE);
		model.setNamespace("rdfs", RDFS.NAMESPACE);
		model.setNamespace("xsd", XMLSchema.NAMESPACE);
		model.setNamespace("foaf", FOAF.NAMESPACE);
		model.setNamespace("ns", namespace);
		model.setNamespace("dbo", dboNamespace);
		
		Rio.write(model, System.out, RDFFormat.TURTLE);
	}
	
	static void execQueryGetEmployees(Repository rep) {
		
		RepositoryConnection conn = rep.getConnection();
		try {
			String queryString = "PREFIX db: <http://dbpedia.org/resource/>" + 
								 "PREFIX onto: <http://dbpedia.org/ontology/>" + 
								 "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
								 "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" + 
								 "PREFIX ns: <" + namespace + ">" +
								 "select distinct ?employee where { "+
								 		"?x rdfs:subClassOf ns:Employee . " +
								 		"?employee rdf:type ?x . " +	
								 "}";
			
			TupleQuery query = conn.prepareTupleQuery(queryString);

			try (TupleQueryResult result = query.evaluate()) {
				while (result.hasNext()) {
					BindingSet solution = result.next();
					String line = "?employee = " + solution.getValue("employee");
					System.out.println(line);
				}
			}
		} finally {
			conn.close();
		}
	}
	
static void execQueryGetHigherDishPrice(Repository rep, Integer limit) {
		
		RepositoryConnection conn = rep.getConnection();
		try {
			String queryString = "PREFIX db: <http://dbpedia.org/resource/>" + 
								 "PREFIX onto: <http://dbpedia.org/ontology/>" + 
								 "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
								 "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" + 
								 "PREFIX ns: <" + namespace + ">" +
								 "select distinct ?price where { "+
								 		"?dish rdf:type ns:Dish . " +
								 		"?dish ns:price ?price . " +	
								 "}" + 
								 "ORDER BY DESC(?price)" + 
								 "LIMIT " + limit.toString();
			
			TupleQuery query = conn.prepareTupleQuery(queryString);

			try (TupleQueryResult result = query.evaluate()) {
				while (result.hasNext()) {
					BindingSet solution = result.next();
					String line = "?price = " + solution.getValue("price");
					System.out.println(line);
				}
			}
		} finally {
			conn.close();
		}
	}

static void execQueryGetReservationOf(Repository rep, String name) {
	
	RepositoryConnection conn = rep.getConnection();
	try {
		String queryString = "PREFIX db: <http://dbpedia.org/resource/>" + 
							 "PREFIX onto: <http://dbpedia.org/ontology/>" + 
							 "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
							 "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" + 
							 "PREFIX ns: <" + namespace + ">" +
							 "select distinct ?reservation where { "+
							 		"?customer rdf:type ns:Customer . " +
							 		"?reservation rdf:type ns:Reservation . " +
							 		"?customer ns:has_made ?reservation . " +
							 		"?customer ns:name ?name . " +
							 		"FILTER(?name = \"" + name + "\")" +
							 "}" ;
		
		TupleQuery query = conn.prepareTupleQuery(queryString);

		try (TupleQueryResult result = query.evaluate()) {
			while (result.hasNext()) {
				BindingSet solution = result.next();
				String line = "?Reservation = " + solution.getValue("reservation");
				System.out.println(line);
			}
		}
	} finally {
		conn.close();
	}
}

static void execQueryGetRestaurantMenuAndDishPrice(Repository rep, String restaurantName) {
	
	RepositoryConnection conn = rep.getConnection();
	try {
		String queryString = "PREFIX db: <http://dbpedia.org/resource/>" + 
							 "PREFIX onto: <http://dbpedia.org/ontology/>" + 
							 "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
							 "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" + 
							 "PREFIX ns: <" + namespace + ">" +
							 "select distinct ?dishAndMenuName ?price where { "+
							 		"?restaurant rdf:type ns:Restaurant . " +
							 		"?restaurant ns:offers ?dishAndMenu . " +
							 		"?restaurant ns:name ?name . " +
							 		"?dishAndMenu ns:name ?dishAndMenuName . " +
							 		"?dishAndMenu ns:price ?price . " +
							 		"FILTER(?name = \"" + restaurantName + "\")" +
							 "}" ;
		
		TupleQuery query = conn.prepareTupleQuery(queryString);

		try (TupleQueryResult result = query.evaluate()) {
			while (result.hasNext()) {
				BindingSet solution = result.next();
				String line = "?dishAndMenuName = " + solution.getValue("dishAndMenuName") + 
						" ?price = " + solution.getValue("price");
				System.out.println(line);
			}
		}
	} finally {
		conn.close();
	}
}

static void execQueryGetEditedAndNonEditedBills(Repository rep) {
	
	RepositoryConnection conn = rep.getConnection();
	try {
		String queryString = "PREFIX db: <http://dbpedia.org/resource/>" + 
							 "PREFIX onto: <http://dbpedia.org/ontology/>" + 
							 "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
							 "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" + 
							 "PREFIX ns: <" + namespace + ">" +
							 "select distinct ?bill ?manager where { "+
							 		"?bill rdf:type ns:Bill_of_sale . " +							 		
							 		"OPTIONAL {" + 
							 		"?manager ns:has_edited ?bill ." + 
							 		"}" +
							 "}" ;
		
		TupleQuery query = conn.prepareTupleQuery(queryString);

		try (TupleQueryResult result = query.evaluate()) {
			while (result.hasNext()) {
				BindingSet solution = result.next();
				String line = "?bill = " + solution.getValue("bill") + 
						" ?manager = " + solution.getValue("manager");
				System.out.println(line);
			}
		}
	} finally {
		conn.close();
	}
}

static void execQueryGetBillsWithMenuOrDishes(Repository rep) {
	
	RepositoryConnection conn = rep.getConnection();
	try {
		String queryString = "PREFIX db: <http://dbpedia.org/resource/>" + 
							 "PREFIX onto: <http://dbpedia.org/ontology/>" + 
							 "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
							 "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" + 
							 "PREFIX ns: <" + namespace + ">" +
							 "select  distinct ?bill where { "+
							 		"{ ?dish rdf:type ns:Dish ." +
							 		" ?bill ns:includes ?dish }" +							 		
							 		" UNION " + 
									"{ ?menu rdf:type ns:Menu ." +
							 		" ?bill ns:includes ?menu }" + 
							 		"}" ;

		TupleQuery query = conn.prepareTupleQuery(queryString);

		try (TupleQueryResult result = query.evaluate()) {
			while (result.hasNext()) {
				BindingSet solution = result.next();
				String line = "?bill = " + solution.getValue("bill");
				System.out.println(line);
			}
		}
	} finally {
		conn.close();
	}
}

	public static void main(String[] args) {
		
		Repository rep = new SailRepository(new MemoryStore());
		
		try {
			buildOntology(rep);
			createIndividuals(rep);
			linkIndividuals(rep);
			
			//Décommenter ici pour afficher les autres résultats de requêtes
//			execQueryGetEmployees(rep);
//			execQueryGetHigherDishPrice(rep, 2);
//			execQueryGetReservationOf(rep, "Marc");
//			execQueryGetRestaurantMenuAndDishPrice(rep, "Le Cygne");
//			execQueryGetEditedAndNonEditedBills(rep);
			execQueryGetBillsWithMenuOrDishes(rep);
		} finally {
			
		}	

	}

}
