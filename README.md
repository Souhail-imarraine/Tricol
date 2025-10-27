# Tricol - Système de Gestion des Fournisseurs

## Description
Tricol est une application de gestion des fournisseurs développée avec Spring Core et configuration XML pure. Cette application permet de gérer efficacement les informations des fournisseurs de l'entreprise Tricol, spécialisée dans la conception et la fabrication de vêtements professionnels.

## Branche: xml-config
Cette branche utilise une **configuration XML 100% pure** sans Spring Data JPA, avec un Repository manuel.

## Technologies Utilisées
- **Java 17**
- **Spring Core 6.0.11** (IoC Container, Dependency Injection)
- **Spring MVC 6.0.11** (REST API)
- **Hibernate 6.2.7** (JPA)
- **MySQL 8.0** (Base de données)
- **Maven** (Gestion des dépendances)
- **Apache Tomcat 10.1** (Serveur d'application)

## Architecture
```
src/main/java/com/tricol/
├── model/
│   └── Fournisseur.java              # Entité JPA
├── repository/
│   ├── FournisseurRepository.java    # Interface Repository
│   └── impl/
│       └── FournisseurRepositoryImpl.java  # Implémentation manuelle
├── service/
│   ├── FournisseurService.java       # Interface Service
│   └── impl/
│       └── FournisseurServiceImpl.java     # Implémentation Service
├── controller/
│   └── FournisseurController.java    # Controller Spring MVC
└── TricoleApplication.java           # Classe principale

src/main/resources/
├── applicationContext.xml            # Configuration Spring (Beans, JPA, Transactions)
└── application.properties            # Propriétés de configuration

src/main/webapp/WEB-INF/
├── web.xml                           # Configuration Servlet
└── dispatcher-servlet.xml            # Configuration Spring MVC
```

## Configuration XML

### 1. applicationContext.xml
- Configuration du DataSource (MySQL)
- Configuration de l'EntityManagerFactory (Hibernate)
- Configuration du TransactionManager
- Déclaration des beans (Repository, Service)
- Injection des dépendances

### 2. dispatcher-servlet.xml
- Configuration Spring MVC
- Déclaration du Controller
- Mapping des URLs

### 3. web.xml
- Configuration du DispatcherServlet
- Configuration du ContextLoaderListener
- Mapping des servlets

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
git checkout xml-config
```

### 2. Créer la base de données
```sql
CREATE DATABASE tricol_db;
```

### 3. Configurer la connexion MySQL
Modifier `src/main/resources/applicationContext.xml` si nécessaire:
```xml
<property name="url" value="jdbc:mysql://localhost:3306/tricol_db"/>
<property name="username" value="root"/>
<property name="password" value=""/>
```

### 4. Compiler le projet
```bash
mvn clean package
```

### 5. Déployer sur Tomcat
1. Copier le fichier WAR généré vers Tomcat webapps
2. Ou déployer via IntelliJ IDEA

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

## Modèle de données

### Fournisseur
```java
- id: Long (Auto-généré)
- societe: String
- adresse: String
- contact: String
- email: String
- telephone: String
- ville: String
- ice: String (Identifiant Commun Entreprise)
```

## Concepts Spring Core Utilisés

### 1. IoC Container (Inversion of Control)
- Spring gère le cycle de vie des objets
- Configuration via XML (applicationContext.xml)

### 2. Dependency Injection
- Injection par setter dans les beans
- Injection de l'EntityManager via @PersistenceContext
- Autowiring byType pour le Service

### 3. Spring Beans
- Repository Bean (FournisseurRepositoryImpl)
- Service Bean (FournisseurServiceImpl)
- Controller Bean (FournisseurController)

### 4. Transaction Management
- Gestion déclarative des transactions avec @Transactional
- Configuration du TransactionManager dans XML

### 5. Spring MVC
- DispatcherServlet pour router les requêtes
- Controller implémentant l'interface Controller
- Mapping manuel des URLs dans XML

## Configuration XML vs Annotations

Cette branche démontre la configuration XML pure:
- ✅ Tous les beans déclarés dans XML
- ✅ Injection des dépendances via XML
- ✅ Configuration JPA/Hibernate via XML
- ✅ Repository manuel (sans Spring Data JPA)
- ⚠️ Annotations minimales (@Entity, @PersistenceContext, @Transactional)

## Différences avec la branche main

| Aspect | Branche main | Branche xml-config |
|--------|--------------|--------------------|
| Configuration | Annotations + Java Config | XML pur |
| Repository | Spring Data JPA | Implémentation manuelle |
| Controller | @RestController | implements Controller |
| Injection | @Autowired | XML setter injection |
| Complexité | Simple | Plus complexe |

## Problèmes Connus

La configuration XML pure avec Spring moderne présente des limitations:
- Spring Data JPA ne fonctionne pas bien avec XML pur
- Repository manuel nécessaire
- Controller ancien style (moins pratique)
- Configuration plus verbeuse

## Auteur
**Projet Tricol** - Gestion des Fournisseurs

## Licence
Projet académique - 2025
