package org.smarttechie.repository;

import org.smarttechie.entity.Product;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CassandraRepository<Product, Integer>  {
}
