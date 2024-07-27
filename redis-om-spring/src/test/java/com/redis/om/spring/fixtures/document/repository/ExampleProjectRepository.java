package com.redis.om.spring.fixtures.document.repository;

import com.redis.om.spring.fixtures.document.model.ExampleProject;
import com.redis.om.spring.repository.RedisDocumentRepository;

@SuppressWarnings("unused")
public interface ExampleProjectRepository extends RedisDocumentRepository<ExampleProject, Long> {
}
