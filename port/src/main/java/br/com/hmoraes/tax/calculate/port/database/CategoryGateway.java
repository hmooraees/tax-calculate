package br.com.hmoraes.tax.calculate.port.database;

public interface CategoryGateway {

    String findById(Long id);

    Long findByName(String name);

    public interface NotFound {}

    public class CategoryNotFoundException extends RuntimeException implements NotFound {
    }
}
