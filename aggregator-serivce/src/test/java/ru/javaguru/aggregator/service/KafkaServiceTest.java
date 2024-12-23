package ru.javaguru.aggregator.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KafkaServiceTest {

    @Autowired
    private KafkaService service;

    @Test
    public void test() {
        String experienceJson = """
[
  {
    "id": 1,
    "sequenceNumber": 1,
    "periodFrom": "2022-01",
    "periodTo": "2023-12",
    "presentTime": false,
    "industryId": 101,
    "industryName": "Software Development",
    "company": "TechCorp Inc.",
    "position": "Software Engineer",
    "duties": ["Developed REST APIs", "Optimized performance"],
    "achievements": "Promoted to lead developer",
    "link": "https://www.techcorp.com"
  },
  {
    "id": 2,
    "sequenceNumber": 2,
    "periodFrom": "2021-06",
    "periodTo": "2022-06",
    "presentTime": false,
    "industryId": 102,
    "industryName": "Finance",
    "company": "FinanceCorp",
    "position": "Analyst",
    "duties": ["Analyzed data", "Generated reports"],
    "achievements": "Improved reporting efficiency",
    "link": "https://www.financecorp.com"
  }
]

                """;
        service.consume("experience-service", experienceJson);
    }

}