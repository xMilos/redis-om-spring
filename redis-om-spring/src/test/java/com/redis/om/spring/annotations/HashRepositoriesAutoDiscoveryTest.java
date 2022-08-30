package com.redis.om.spring.annotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import com.redis.om.spring.AbstractBaseOMTest;
import com.redis.om.spring.TestConfig;

@SpringBootTest( //
    classes = HashRepositoriesAutoDiscoveryTest.Config.class, //
    properties = { "spring.main.allow-bean-definition-overriding=true" } //
)
class HashRepositoriesAutoDiscoveryTest extends AbstractBaseOMTest {
  @SpringBootApplication
  @Configuration
  @EnableRedisEnhancedRepositories(basePackageClasses = {})
  static class Config extends TestConfig {
  }

  @Autowired
  AHashRepository repository;

  @Test
  void testBasePackageClassesAreFound() {
    AHash aHash = repository.save(AHash.of("A Doc"));

    assertEquals(1, repository.count());

    Optional<AHash> maybeDoc = repository.findById(aHash.getId());

    assertTrue(maybeDoc.isPresent());

    // delete given an id
    repository.deleteById(aHash.getId());

    assertEquals(0, repository.count());
  }

}