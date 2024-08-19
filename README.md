# Gaming Service

## Overview

This project is part of a gaming service responsible for tracking all-time top scores. As players complete a game, the game service publishes the player's score to a Kafka topic (or a flat file). This service processes these scores and maintains a leaderboard, returning the top 5 scores and the corresponding player names upon request.

## Assumptions

- **Player and Score Validation**: The gaming service guarantees that all published scores belong to either registered or valid guest users. No further verification of player identity or score integrity is necessary.
- **Player Ranking**: In case of identical scores, player positions on the leaderboard are determined by reverse lexicographical order of their unique usernames.
- **Data Consistency**: Player data is updated sequentially, preventing concurrent modifications from the same player.
- **Score Structure**: Scores are represented as simple numerical values. The leaderboard displays the top N scores along with their corresponding usernames, where N is a configurable parameter.
- **Internal API Usage**: All APIs are exclusively used within the gaming service ecosystem, rendering authentication and data validation superfluous.
- **Leaderboard Structure**: Currently, a single leaderboard is maintained. The system is designed to accommodate multiple leaderboards in future expansions.
- **Scalability**: The current system efficiently handles the present workload on a single server. Additional servers can be integrated to accommodate increased traffic.

## Installation

### Prerequisites

- Java 17 or higher
- Springboot 3.0.0 or higher
- Apache Kafka
- Any supported database (e.g., MySQL, PostgreSQL)

### Steps to Install and Run Kafka Locally

1. **Download Kafka:**
    - Download Kafka from the [Apache Kafka website](https://kafka.apache.org/downloads).
    - Extract the downloaded Kafka files to a preferred directory.

2. **Start Zookeeper:**
    - Kafka requires Zookeeper to manage its cluster. Navigate to the Kafka directory and run the following command to start Zookeeper:
      ```bash
      bin/zookeeper-server-start.sh config/zookeeper.properties
      ```

3. **Start Kafka Broker:**
    - In another terminal window, start the Kafka broker with the following command:
      ```bash
      bin/kafka-server-start.sh config/server.properties
      ```

4. **Create a Kafka Topic:**
    - Create a topic for your gaming service:
      ```bash
      bin/kafka-topics.sh --create --topic game-scores --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
      ```

5. **Verify the Topic:**
    - To ensure that the topic was created successfully, run:
      ```bash
      bin/kafka-topics.sh --list --bootstrap-server localhost:9092
      ```

6. **Start Producing and Consuming Messages:**
    - You can produce and consume messages to the topic using the following commands:
        - **Producer:**
          ```bash
          bin/kafka-console-producer.sh --topic game-scores --bootstrap-server localhost:9092
          ```
        - **Consumer:**
          ```bash
          bin/kafka-console-consumer.sh --topic game-scores --from-beginning --bootstrap-server localhost:9092
          ```

## Testing

- Make sure to run the test cases provided in the project to verify the functionality.
- The project includes key unit tests to ensure the correctness of the leaderboard service.

## Conclusion

This gaming service module is designed to be scalable, maintainable, and extendable. By following the steps above, you can set up the service locally and test its core functionality.
