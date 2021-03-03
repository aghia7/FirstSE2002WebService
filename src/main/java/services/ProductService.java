package services;

import entities.Product;
import repositories.interfaces.IProductRepository;
import services.interfaces.IProductService;

import javax.inject.Inject;

public class ProductService implements IProductService {
    @Inject
    private IProductRepository productRepository;

    @Override
    public Product getProductById(int id) {
        return productRepository.get(id);
    }
}
