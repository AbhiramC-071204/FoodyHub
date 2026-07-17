
link : scholaraihublearner.lovable.app
#  Technology Stack

**Frontend:** HTML5, CSS3, JavaScript (ES6+), Ionicons
**Backend:** Java Servlets, JSP, DAO/DTO patterns, JDBC
**Automation:** Python scripts for menu image handling and data management

# Architecture (MVC)

**Controller:** Servlets (com.mainproject) handle requests like Cart, Checkout, Login, Menu, Restaurant.
**Model:** DAO, DTO, and connector classes (pro1) manage data and database interactions.
**View:** JSP/HTML (webapp) renders dynamic pages.
#  Project Structure

**FoodOrderingAppl/
├── src/main/java/
│   ├── com.mainproject/  # Servlets
│   ├── pro1/             # DAO, DTO, Connector
│   └── jdbc/             # JDBC resources
├── webapp/               # Frontend assets & views
│   ├── assets/
│   ├── css/
│   ├── images/
│   ├── JS/
│   ├── WEB-INF/
│   ├── cart.jsp
│   ├── checkout.jsp
│   ├── index.html
│   ├── login.jsp
│   ├── menu.jsp
│   └── restaurant.jsp
└── README.md**
