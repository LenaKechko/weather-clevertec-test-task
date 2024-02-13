package ru.clevertec.weathertesttask.config;

import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class MongoContainerInitializer {

    private static final DockerImageName MONGO_IMAGE = DockerImageName.parse("mongo:latest");

    @ClassRule
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer(MONGO_IMAGE)
            .withEnv("MONGO_INITDB_DATABASE", "weather_database_test");

    static {
        mongoDBContainer.start();
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.port", () -> mongoDBContainer.getFirstMappedPort());
    }

}
