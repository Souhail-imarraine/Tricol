# Tricol - Système de Gestion des Fournisseurs

## Description
Tricol est une application de gestion des fournisseurs développée avec Spring Core et configuration par annotations. Cette application permet de gérer efficacement les informations des fournisseurs de l'entreprise Tricol, spécialisée dans la conception et la fabrication de vêtements professionnels.

## Branche: main (Annotations + Java Config)
Cette branche utilise une **configuration moderne avec annotations et Java Config**.

## Technologies Utilisées
- **Java 17**
- **Spring Core 6.0.11** (IoC Container, Dependency Injection)
- **Spring MVC 6.0.11** (REST API)
- **Spring Data JPA 3.1.2** (Accès aux données)
- **Hibernate 6.2.7** (JPA)
- **MySQL 8.0** (Base de données)
- **Maven** (Gestion des dépendances)
- **Apache Tomcat 10.1** (Serveur d'application)
- **Jackson 2.15.2** (Sérialisation JSON)

## Architecture
```
src/main/java/com/tricol/
├── model/
│   └── Fournisseur.java              # Entité JPA (@Entity)
├── repository/
│   └── FournisseurRepository.java    # Repository Spring Data JPA (@Repository)
├── service/
│   ├── FournisseurService.java       # Interface Service
│   └── impl/
│       └── FournisseurServiceImpl.java     # Implémentation (@Service)
├── controller/
│   └── FournisseurController.java    # REST Controller (@RestController)
├── config/
│   ├── AppConfig.java                # Configuration Java (@Configuration)
│   └── WebConfig.java                # Configuration Web MVC
└── TricoleApplication.java           # Classe principale

src/main/resources/
├── application.properties            # Propriétés de configuration
└── applicationContext.xml            # Configuration XML (optionnelle)

src/main/webapp/WEB-INF/
└── web.xml                           # Configuration Servlet
```

## Configuration par Annotations

### 1. @Configuration - AppConfig.java
```java
@Configuration
@ComponentScan(basePackages = "com.tricol")
@EnableJpaRepositories(basePackages = "com.tricol.repository")
@EnableTransactionManagement
public class AppConfig {
    // Configuration DataSource, EntityManager, TransactionManager
}
```

### 2. @RestController - FournisseurController.java
```java
@RestController
@RequestMapping("/api/v1/fournisseurs")
public class FournisseurController {
    @Autowired
    private FournisseurService service;
    
    @GetMapping
    public List<Fournisseur> getAll() { ... }
}
```

### 3. @Service - FournisseurServiceImpl.java
```java
@Service
public class FournisseurServiceImpl implements FournisseurService {
    @Autowired
    private FournisseurRepository repository;
}
```

### 4. @Repository - FournisseurRepository.java
```java
@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
    // Méthodes générées automatiquement par Spring Data JPA
}
```

## Prérequis
1. **Java JDK 17** ou supérieur
2. **Maven 3.6+**
3. **MySQL 8.0+**
4. **Apache Tomcat 10.1+**
5. **IDE** (IntelliJ IDEA recommandé)

## Installation

### 1. Cloner le projet
```bash
git clone <repository-url>
cd tricole_nota
git checkout main
```

### 2. Créer la base de données
```sql
CREATE DATABASE tricol_db;
```

### 3. Configurer la connexion MySQL
Modifier `src/main/java/com/tricol/config/AppConfig.java`:
```java
dataSource.setUrl("jdbc:mysql://localhost:3306/tricol_db");
dataSource.setUsername("root");
dataSource.setPassword("");
```

### 4. Compiler le projet
```bash
mvn clean package
```

### 5. Déployer sur Tomcat
1. Copier le fichier WAR généré vers Tomcat webapps
2. Ou déployer via IntelliJ IDEA
3. Accéder à: `http://localhost:8080/tricole_nota`

## API REST Endpoints

### Base URL
```
http://localhost:8080/tricole_nota/api/v1/fournisseurs
```

### Endpoints disponibles

| Méthode | URL | Description |
|---------|-----|-------------|
| GET | `/api/v1/fournisseurs` | Lister tous les fournisseurs |
| GET | `/api/v1/fournisseurs/{id}` | Récupérer un fournisseur par ID |
| POST | `/api/v1/fournisseurs` | Créer un nouveau fournisseur |
| PUT | `/api/v1/fournisseurs/{id}` | Modifier un fournisseur |
| DELETE | `/api/v1/fournisseurs/{id}` | Supprimer un fournisseur |

## Tests avec Postman

### 1. GET - Lister tous les fournisseurs
```
GET http://localhost:8080/tricole_nota/api/v1/fournisseurs
```
**Réponse:** `200 OK`
```json
[
  {
    "id": 1,
    "societe": "Zara Maroc",
    "adresse": "Boulevard Mohammed V, Casablanca",
    "contact": "Ahmed Bennani",
    "email": "contact@zara.ma",
    "telephone": "0522123456",
    "ville": "Casablanca",
    "ice": "001234567890001"
  }
]
```

### 2. POST - Créer un fournisseur
```
POST http://localhost:8080/tricole_nota/api/v1/fournisseurs
Content-Type: application/json

{
  "societe": "Zara Maroc",
  "adresse": "Boulevard Mohammed V, Casablanca",
  "contact": "Ahmed Bennani",
  "email": "contact@zara.ma",
  "telephone": "0522123456",
  "ville": "Casablanca",
  "ice": "001234567890001"
}
```
**Réponse:** `200 OK` avec l'objet créé

### 3. GET - Récupérer par ID
```
GET http://localhost:8080/tricole_nota/api/v1/fournisseurs/1
```

### 4. PUT - Modifier un fournisseur
```
PUT http://localhost:8080/tricole_nota/api/v1/fournisseurs/1
Content-Type: application/json

{
  "societe": "Zara Maroc SARL",
  "adresse": "Boulevard Mohammed V, Casablanca",
  "contact": "Ahmed Bennani",
  "email": "contact@zara.ma",
  "telephone": "0522123456",
  "ville": "Casablanca",
  "ice": "001234567890001"
}
```

### 5. DELETE - Supprimer un fournisseur
```
DELETE http://localhost:8080/tricole_nota/api/v1/fournisseurs/1
```
**Réponse:** `200 OK`

## Modèle de données

### Fournisseur
```java
@Entity
@Table(name = "fournisseurs")
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String societe;
    private String adresse;
    private String contact;
    private String email;
    private String telephone;
    private String ville;
    private String ice;  // Identifiant Commun Entreprise
}
```

## Concepts Spring Core Utilisés

### 1. IoC Container (Inversion of Control)
- Spring gère le cycle de vie des objets
- Configuration via annotations (@Configuration, @Bean)
- ApplicationContext charge tous les beans au démarrage

### 2. Dependency Injection
- **@Autowired**: Injection automatique des dépendances
- **Constructor Injection**: Recommandé pour les dépendances obligatoires
- **Setter Injection**: Pour les dépendances optionnelles

### 3. Spring Beans & Scopes
- **@Component**: Bean générique
- **@Service**: Bean de logique métier
- **@Repository**: Bean d'accès aux données
- **@Controller/@RestController**: Bean de présentation
- **Scope Singleton**: Par défaut (une seule instance)

### 4. Spring Data JPA
- **JpaRepository**: Interface avec méthodes CRUD automatiques
- **Query Methods**: Génération automatique de requêtes
  - `findAll()`, `findById()`, `save()`, `deleteById()`
- **Custom Queries**: Méthodes personnalisées
  - `findBySociete(String societe)`
  - `findByEmailEndingWith(String domain)`

### 5. Transaction Management
- **@Transactional**: Gestion déclarative des transactions
- **@EnableTransactionManagement**: Active la gestion des transactions
- Rollback automatique en cas d'exception

### 6. Spring MVC
- **@RestController**: Combine @Controller + @ResponseBody
- **@RequestMapping**: Mapping des URLs
- **@GetMapping, @PostMapping, @PutMapping, @DeleteMapping**
- **@PathVariable**: Extraction des paramètres d'URL
- **@RequestBody**: Désérialisation JSON → Objet Java

## Annotations Principales

### Configuration
- `@Configuration`: Classe de configuration Spring
- `@ComponentScan`: Scan automatique des composants
- `@EnableJpaRepositories`: Active Spring Data JPA
- `@EnableTransactionManagement`: Active les transactions
- `@Bean`: Déclare un bean Spring

### Stéréotypes
- `@Component`: Composant Spring générique
- `@Service`: Couche service (logique métier)
- `@Repository`: Couche d'accès aux données
- `@Controller`: Contrôleur MVC
- `@RestController`: Contrôleur REST

### Injection de dépendances
- `@Autowired`: Injection automatique
- `@Qualifier`: Spécifie quel bean injecter
- `@Value`: Injection de valeurs depuis properties

### JPA
- `@Entity`: Entité JPA
- `@Table`: Nom de la table
- `@Id`: Clé primaire
- `@GeneratedValue`: Génération automatique de l'ID
- `@Column`: Configuration de colonne

### Web MVC
- `@RequestMapping`: Mapping d'URL
- `@GetMapping`: GET HTTP
- `@PostMapping`: POST HTTP
- `@PutMapping`: PUT HTTP
- `@DeleteMapping`: DELETE HTTP
- `@PathVariable`: Variable d'URL
- `@RequestBody`: Corps de la requête
- `@ResponseBody`: Corps de la réponse

### Transactions
- `@Transactional`: Méthode transactionnelle

## Avantages de la Configuration par Annotations

✅ **Code plus concis**: Moins de XML verbeux
✅ **Type-safe**: Erreurs détectées à la compilation
✅ **Refactoring facile**: IDE supporte bien les annotations
✅ **Convention over Configuration**: Moins de configuration
✅ **Spring Data JPA**: Génération automatique des repositories
✅ **Moderne**: Pratique standard depuis Spring 3.0+

## Comparaison: Annotations vs XML

| Aspect | Annotations (main) | XML (xml-config) |
|--------|-------------------|------------------|
| Configuration | @Configuration, @Bean | applicationContext.xml |
| Beans | @Component, @Service | <bean id="..."> |
| Injection | @Autowired | <property name="..."> |
| Repository | Spring Data JPA | Implémentation manuelle |
| Controller | @RestController | implements Controller |
| Verbosité | Minimal | Élevée |
| Maintenabilité | Excellente | Moyenne |
| Type Safety | Oui | Non |
| Refactoring | Facile | Difficile |

## Structure du Projet

### Couche Model
- Entités JPA avec annotations
- POJOs avec getters/setters

### Couche Repository
- Interfaces étendant JpaRepository
- Méthodes CRUD automatiques
- Query Methods personnalisés

### Couche Service
- Interface + Implémentation
- Logique métier
- Gestion des transactions

### Couche Controller
- REST API endpoints
- Validation des données
- Gestion des réponses HTTP

## Bonnes Pratiques Appliquées

1. **Séparation des couches**: Model, Repository, Service, Controller
2. **Injection par interface**: Couplage faible
3. **Gestion des transactions**: @Transactional sur les services
4. **REST API**: Respect des conventions HTTP
5. **Nommage cohérent**: Conventions Java standard
6. **Exception handling**: Gestion centralisée des erreurs

## Tests

Importer la collection Postman fournie: `Tricol_API_Tests.postman_collection.json`

## Branches du Projet

- **main**: Configuration par annotations (RECOMMANDÉ)
- **xml-config**: Configuration XML pure (Académique)

## Auteur
**Projet Tricol** - Gestion des Fournisseurs

## Licence
Projet académique - 2025
