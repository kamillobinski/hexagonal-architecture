<a name="readme-top"></a>



<!-- PROJECT LOGO -->
<br />
<div align="center">

[//]: # (  <img src="assets/logo.png" alt="Logo" width="80" height="80">)
  <h3 align="center">Hexagonal-Architecture</h3>
  <p align="center">
    Tutorial repository representing a hexagonal Spring Boot
    <br>application. With PostgreSQL integration, it demonstrates
    <br>balance, deposit, and withdrawal endpoints
    <br>for a cash machine.
    <br /><br />
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#license">License</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

This repository is designed to provide developers with a practical understanding of hexagonal architecture in Java. The project showcases a cash machine application implemented in plain Java, emphasizing flexibility and ease of adapting to different frameworks.

The application's core logic and domain models are written in plain Java, ensuring modularity and decoupling from any specific framework. This design approach allows for seamless transitions or framework changes, offering developers the freedom to switch from Spring to another framework if needed.

With the integration of PostgreSQL for data management, developers can explore the implementation of balance, deposit, and withdrawal endpoints in a hexagonal architecture context. By studying this repository, developers can gain valuable insights into building flexible and maintainable Java applications while keeping the door open for future framework adaptability.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With

![Java][Java-url]
![Spring][Spring-url]
![PostgreSQL][PostgreSQL-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple steps.

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/kamillobinski/hexagonal-architecture.git
   ```

2. Update database variables in the application.yml
   ```yaml
   url: jdbc:postgresql://HOST:PORT/DB
   username: DB_USERNAME
   password: DB_PASSWORD
   ```

3. Run the command to start the Spring Boot application (make sure you have Java and Maven installed on your system)
   ```sh
   cd ./cash-machine-bootstrap
   mvn spring-boot:run
   ```

Alternatively, you can use Docker Compose to simplify the setup process:

1. Ensure that you have Docker and Docker Compose installed on your system.
2. Open a terminal and navigate to the project's root directory.
3. Run the following command to build the app and create the PostgreSQL database:
   ```sh
   docker-compose up --build -d
   ```
   Docker Compose will handle the containerization of the application and automatically set up the PostgreSQL database for you.

   Once the process is complete, the Spring Boot application will be running, and you can access it using the endpoints.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE -->
## Usage

After setting up and running the application, you can interact with the following endpoints to perform various operations:

1. **Deposit**: To deposit a specified amount into the cash machine, send a POST request to `/deposit/{amount}`. Replace `{amount}` with the desired deposit amount. For example:
   ```sh
   POST /deposit/100.00
   ```
   ```json
   {
      "cashMachine": {
         "id": 1,
         "balance": 1100.00
      },
      "balanceBefore": 1000.00,
      "balanceAfter": 1100.00,
      "eventType": "DEPOSIT",
      "timestamp": "2023-05-13T18:28:18.177313671Z"
   }
   ```
   This will deposit 100 units of currency into the cash machine.

2. **Withdraw**: To withdraw a specified amount from the cash machine, send a POST request to `/withdraw/{amount}`. Replace `{amount}` with the desired withdrawal amount. For example:
   ```sh
   POST /withdraw/205.50
   ```
   ```json
   {
      "cashMachine": {
         "id": 1,
         "balance": 894.50
      },
      "balanceBefore": 1100.00,
      "balanceAfter": 894.50,
      "eventType": "WITHDRAWAL",
      "timestamp": "2023-05-13T18:30:17.577463254Z"
   }
   ```
   This will withdraw 205.50 units of currency from the cash machine.

3. **Balance**: To check the current balance of the cash machine, send a GET request to `/balance`. For example:
   ```sh
   GET /balance
   ```
   ```json
   {
      "cashMachine": {
         "id": 1,
         "balance": 894.50
      },
      "eventType": "BALANCE",
      "timestamp": "2023-05-13T18:32:45.287229003Z"
   }
   ```
   This will retrieve the current balance of the cash machine.

Ensure that you replace the placeholders {amount} with actual values when making requests. 

Upon making these API calls, you will receive the appropriate response indicating the success or failure of the operation.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
[Java-url]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white
[Spring-url]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[PostgreSQL-url]: https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white
