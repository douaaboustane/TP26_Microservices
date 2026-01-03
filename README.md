# TP26 – Microservices Observables et Résilients

## 📌 Description
Ce TP met en œuvre une architecture **microservices Spring Boot** orientée **observabilité et résilience**, déployée avec **Docker Compose**.  
Le projet comprend plusieurs services indépendants (Book Service, Pricing Service), une base de données MySQL et une orchestration complète via Docker.

L’objectif principal est de :
- Déployer plusieurs instances de services
- Observer leur exécution
- Vérifier leur bon fonctionnement dans un environnement conteneurisé

---

## 🗂️ Structure du projet
- **book-service** : Service Spring Boot principal
- **pricing-service** : Service Spring Boot secondaire
- **docker-compose.yml** : Orchestration des services
- **MySQL** : Base de données utilisée par les services

---

## ▶️ Démarrage de l’application (Spring Boot)

L’application Spring Boot démarre correctement depuis IntelliJ IDEA, comme montré ci-dessous.  
Les logs indiquent :
- Initialisation de Hibernate
- Connexion à la base de données
- Démarrage du serveur Tomcat
- Application prête à recevoir des requêtes

![Démarrage de l’application Spring Boot](doc.png)

---

## 🐳 Déploiement avec Docker Compose

Les services sont lancés via **Docker Compose**.  
Plusieurs instances de `book-service` sont déployées pour simuler la montée en charge et la résilience.

### 📦 Conteneurs actifs
- book-service (plusieurs instances)
- pricing-service
- mysql

![Conteneurs Docker en cours d’exécution](26%201.png)

---

## 📊 Supervision via Docker Desktop

Docker Desktop permet de visualiser :
- Les conteneurs en cours d’exécution
- Les ports exposés
- L’utilisation CPU et mémoire
- L’état global du système

Cela confirme que tous les services sont bien déployés et fonctionnels.

![Vue globale Docker Desktop](26%202.png)

---

## ✅ Conclusion
Ce TP valide :
- Le bon fonctionnement d’une architecture microservices
- L’intégration Spring Boot + Docker
- Le déploiement multi-instances
- L’observabilité via Docker Desktop

Le système est opérationnel, scalable et prêt pour des tests de résilience et de charge.
