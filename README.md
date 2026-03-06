# School Management (Spring Boot 3 / MVC + Thymeleaf + JPA + MySQL)

## ✅ Fonctionnel (réel)
- Authentification **Admin** / **Étudiant** (Spring Security)
- Gestion complète (Admin): Professeurs, Matières, Étudiants, Notes (CRUD complet)
- Bulletin étudiant + **moyenne pondérée**
- **Export PDF** du bulletin (PDFBox)
- **Statistiques**: moyenne globale, moyennes par matière, classement étudiants

---

## Prérequis
- Java 17
- Maven
- MySQL

---

## 1) Créer la base MySQL
```sql
CREATE DATABASE school_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

## 2) Configurer MySQL
Éditer `src/main/resources/application.properties`:
- `spring.datasource.username`
- `spring.datasource.password`

## 3) Lancer
```bash
mvn spring-boot:run
```

Puis ouvrir:
- http://localhost:8080

---

## Comptes de démo (créés au 1er lancement)
- Admin: `admin` / `admin123`
- Étudiant: `student1` / `student123`

> Tu peux ensuite créer tes propres étudiants/profs/matières/notes via l’interface Admin.

---

## PDF
- Dans un bulletin, clique **PDF** (admin ou étudiant).

## Statistiques
- Admin → menu **Stats**
